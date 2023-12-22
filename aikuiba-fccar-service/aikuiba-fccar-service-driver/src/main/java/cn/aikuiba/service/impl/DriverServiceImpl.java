package cn.aikuiba.service.impl;

import cn.aikuiba.constants.ErrorCode;
import cn.aikuiba.pojo.app.dto.MinappDriverRegisterDTO;
import cn.aikuiba.pojo.domain.Driver;
import cn.aikuiba.mapper.DriverMapper;
import cn.aikuiba.pojo.properties.MiniProgramProperties;
import cn.aikuiba.pojo.result.MinappOpenIdResult;
import cn.aikuiba.service.IDriverService;
import cn.aikuiba.service.IDriverSettingService;
import cn.aikuiba.service.IDriverSummaryService;
import cn.aikuiba.service.IDriverWalletService;
import cn.aikuiba.utils.AssertUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
    private MiniProgramProperties minappProperties;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private IDriverSettingService driverSettingService;

    @Autowired
    private IDriverWalletService driverWalletService;

    @Autowired
    private IDriverSummaryService driverSummaryService;


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
     * @param minappDriverRegisterDTO 注册对象
     */
    @Override
    public void register(MinappDriverRegisterDTO minappDriverRegisterDTO) {
        String urlOpenid = minappProperties.getUrlOpenid();
        // https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code
        urlOpenid = String.format(urlOpenid, minappProperties.getAppId(), minappProperties.getAppSecret(), minappDriverRegisterDTO.getCode());

        // 使用RestTemplate发送请求换取OPENID
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(urlOpenid, String.class);
        // 判断是否成功获取到OPENID
        AssertUtil.isEquals(responseEntity.getStatusCodeValue(), HttpStatus.OK.value(), ErrorCode.MINAPP_RESULT_OPENID_EMPTY);
        // 判断是否获取OPENID成功
        log.info("小程序-司机端 -Result -{}", responseEntity);
        /*{
            "openid":"xxxxxx",
            "session_key":"xxxxx",
            "unionid":"xxxxx",
            "errcode":0,
            "errmsg":"xxxxx"
        }*/
        MinappOpenIdResult openidResult = JSON.parseObject(responseEntity.getBody(), MinappOpenIdResult.class);
        // 如果想链式调用就必须在后面泛型加Driver
        LambdaQueryWrapper<Driver> query = new LambdaQueryWrapper<Driver>().eq(Driver::getOpenId, openidResult.getOpenid());
        Driver driverInDb = super.getOne(query);
        //判断是否openid已注册
        AssertUtil.isNull(driverInDb, ErrorCode.PARAM_PHONE_EXIST);
        // 保存司机及司机相关数据
        addDriverAbout(openidResult.getOpenid());
    }

    /**
     * 保存司机相关数据
     *
     * @param openId 司机唯一ID
     */
    private void addDriverAbout(String openId) {
        //获取唯一ID
        long driverId = IdUtil.createSnowflake(1, 1).nextId();
        Driver driver = new Driver();
        driver.setId(driverId);
        driver.setBitState(0L);
        driver.setCreateTime(new Date());
        driver.setOpenId(openId);
        save(driver);
        // 4.保存司机接单配置
        driverSettingService.addSetting(driverId);
        // 5.保存司机钱包配置
        driverWalletService.addWallet(driverId);
        // 6.保存结算配置
        driverSummaryService.addSummary(driverId);
    }
}
