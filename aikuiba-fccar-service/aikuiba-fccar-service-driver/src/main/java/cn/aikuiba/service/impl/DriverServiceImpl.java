package cn.aikuiba.service.impl;

import cn.aikuiba.api.remote.api.LoginFeignAPI;
import cn.aikuiba.api.remote.pojo.dto.LoginDto;
import cn.aikuiba.constants.Constants;
import cn.aikuiba.constants.ErrorCode;
import cn.aikuiba.mapper.DriverMapper;
import cn.aikuiba.pojo.app.dto.MiniProgramRegisterDTO;
import cn.aikuiba.pojo.domain.Driver;
import cn.aikuiba.pojo.result.PhoneNumberResult;
import cn.aikuiba.result.JSONResult;
import cn.aikuiba.result.MiniProgramOpenIdResult;
import cn.aikuiba.service.IDriverService;
import cn.aikuiba.service.IDriverSettingService;
import cn.aikuiba.service.IDriverSummaryService;
import cn.aikuiba.service.IDriverWalletService;
import cn.aikuiba.template.WeChatTemplate;
import cn.aikuiba.utils.AssertUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 司机对象 服务实现类
 * </p>
 *
 * @author 蛮小满Sama
 * @since 2023-12-19
 */
@Slf4j
@Service
public class DriverServiceImpl extends ServiceImpl<DriverMapper, Driver> implements IDriverService {
    @Autowired
    private IDriverSettingService driverSettingService;
    @Autowired
    private IDriverWalletService driverWalletService;
    @Autowired
    private IDriverSummaryService driverSummaryService;

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
        // 如果想链式调用就必须在后面泛型加Driver
        LambdaQueryWrapper<Driver> query = new LambdaQueryWrapper<Driver>().eq(Driver::getOpenId, openidResult.getOpenid());
        Driver driverInDb = super.getOne(query);
        // 判断是否openid已注册
        AssertUtil.isNull(driverInDb, ErrorCode.PARAM_PHONE_EXIST);
        // 获取司机手机号(应当在能注册后)
        //        PhoneNumberResult phoneNumberResult = weChatTemplate.getPhoneNumber(dto.getPhoneCode());
        //        AssertUtil.isNotNull(phoneNumberResult.getPhone_info(), ErrorCode.APP_GET_PHONE_ERROR);
        //        String phoneNumber = phoneNumberResult.getPhone_info().getPurePhoneNumber();
        // 个人开发者无法获取微信用户的手机号码,这里写死
        String phoneNumber = "19981259717";
        // 6保存司机及司机相关数据
        Long driverId = addDriverAbout(openidResult.getOpenid(), phoneNumber);
        //7.保存司机登录信息
        LoginDto loginDto = new LoginDto();
        loginDto.setId(driverId);
        loginDto.setType(Constants.Login.TYPE_DRIVER);
        loginDto.setOpenId(openidResult.getOpenid());
        loginDto.setPhone(phoneNumber);
        JSONResult<Void> loginResult = loginFeignAPI.create(loginDto);
        AssertUtil.isTrue(loginResult.isSuccess(), ErrorCode.APP_DRIVER_LOGIN_ERROR);
    }

    /**
     * 保存司机相关数据
     *
     * @param openId      司机唯一ID
     * @param phoneNumber 手机号码
     */
    private Long addDriverAbout(String openId, String phoneNumber) {
        //获取唯一ID
        long driverId = IdUtil.createSnowflake(1, 1).nextId();
        Driver driver = new Driver();
        driver.setId(driverId);
        driver.setBitState(0L);
        driver.setCreateTime(new Date());
        driver.setOpenId(openId);
        driver.setPhone(phoneNumber);
        save(driver);
        // 4.保存司机接单配置
        driverSettingService.addSetting(driverId);
        // 5.保存司机钱包配置
        driverWalletService.addWallet(driverId);
        // 6.保存结算配置
        driverSummaryService.addSummary(driverId);
        return driverId;
    }
}
