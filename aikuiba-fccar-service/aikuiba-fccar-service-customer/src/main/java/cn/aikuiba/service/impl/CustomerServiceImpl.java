package cn.aikuiba.service.impl;

import cn.aikuiba.api.remote.api.LoginFeignAPI;
import cn.aikuiba.api.remote.pojo.dto.LoginDto;
import cn.aikuiba.constants.Constants;
import cn.aikuiba.constants.ErrorCode;
import cn.aikuiba.pojo.app.dto.MiniProgramRegisterDTO;
import cn.aikuiba.pojo.domain.Customer;
import cn.aikuiba.mapper.CustomerMapper;
import cn.aikuiba.pojo.result.PhoneNumberResult;
import cn.aikuiba.result.JSONResult;
import cn.aikuiba.result.MiniProgramOpenIdResult;
import cn.aikuiba.service.ICustomerService;
import cn.aikuiba.service.ICustomerSummaryService;
import cn.aikuiba.service.ICustomerWalletService;
import cn.aikuiba.template.WeChatTemplate;
import cn.aikuiba.utils.AssertUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 客户 服务实现类
 * </p>
 *
 * @author 蛮小满Sama
 * @since 2023-12-23
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {

    @Autowired
    private ICustomerWalletService customerWalletService;
    @Autowired
    private ICustomerSummaryService customerSummaryService;
    @Autowired
    private WeChatTemplate weChatTemplate;
    @Autowired
    private LoginFeignAPI loginFeignAPI;


    /**
     * <pre>
     *  1.获取授权码
     *  2.获取OpenId
     *      地址:GET https://api.weixin.qq.com/sns/jscode2session
     *      参数:appid,secret,js_code,grant_type(固定值authorization_code)
     *      AppID(小程序ID)			wx9212eba82d982dba
     *      AppSecret(小程序密钥)44cc74562f4efbd5d1881266197e47ab
     *  3.判断是否注册
     *      3.1 未注册 --完成注册流程
     *      3.2 已注册 --报错
     *  4.保存司机接单配置
     *  5.保存司机钱包配置
     *  6.保存司机结算配置
     *  7.保存司机登录信息
     * </pre>
     *
     * @param dto 注册对象
     */
    @Override
    public void register(MiniProgramRegisterDTO dto) {
        // 使用自封装的微信工具
        MiniProgramOpenIdResult openidResult = weChatTemplate.getOpenid(dto.getLoginCode());
        // 如果想链式调用就必须在后面泛型加Customer
        LambdaQueryWrapper<Customer> query = new LambdaQueryWrapper<Customer>().eq(Customer::getOpenId, openidResult.getOpenid());
        Customer customerInDb = super.getOne(query);
        //判断是否openid已注册
        AssertUtil.isNull(customerInDb, ErrorCode.PARAM_PHONE_EXIST);
        // 获取微信用户手机号码
        PhoneNumberResult phoneNumberResult = weChatTemplate.getPhoneNumber(dto.getPhoneCode());
        // 手机号码
        String phoneNumber = phoneNumberResult.getPhone_info().getPurePhoneNumber();
        // 保存司机及司机相关数据
        Long customerId = addCustomerAbout(openidResult.getOpenid(), phoneNumber);
        //7.保存司机登录信息
        LoginDto loginDto = new LoginDto();
        loginDto.setId(customerId);
        loginDto.setType(Constants.Login.TYPE_CUSTOMER);
        loginDto.setOpenId(openidResult.getOpenid());
        loginDto.setPhone(phoneNumber);
        JSONResult loginResult = loginFeignAPI.create(loginDto);
        AssertUtil.isTrue(loginResult.isSuccess(), ErrorCode.APP_CUSTOMER_LOGIN_ERROR);
    }

    /**
     * 保存司机相关数据
     *
     * @param openId      司机唯一ID
     * @param phoneNumber 手机号码
     */
    private Long addCustomerAbout(String openId, String phoneNumber) {
        //获取唯一ID
        long customerId = IdUtil.createSnowflake(1, 1).nextId();
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setBitState(0L);
        customer.setCreateTime(new Date());
        customer.setOpenId(openId);
        customer.setPhone(phoneNumber);
        save(customer);
        // 5.保存司机钱包配置
        customerWalletService.addWallet(customerId);
        // 6.保存结算配置
        customerSummaryService.addSummary(customerId);
        return customerId;
    }
}
