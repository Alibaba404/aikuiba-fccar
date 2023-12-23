package cn.aikuiba.template;

import cn.aikuiba.constants.ErrorCode;
import cn.aikuiba.properties.MiniProgramProperties;
import cn.aikuiba.result.MinappOpenIdResult;
import cn.aikuiba.utils.AssertUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Created by 蛮小满Sama at 2023/12/23 11:11
 *
 * @description 微信相关工具类
 */
@Slf4j
public class WeChatTemplate {

    private final MiniProgramProperties miniProgramProperties;
    private final RestTemplate restTemplate;

    public WeChatTemplate(MiniProgramProperties miniProgramProperties, RestTemplate restTemplate) {
        this.miniProgramProperties = miniProgramProperties;
        this.restTemplate = restTemplate;
    }

    /**
     * 获取微信OPENID
     *
     * @param weChatCode 微信授权码
     * @return
     */
    public MinappOpenIdResult getOpenid(String weChatCode) {
        String urlOpenid = miniProgramProperties.getUrlOpenid();
        // https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code
        urlOpenid = String.format(urlOpenid, miniProgramProperties.getAppId(), miniProgramProperties.getAppSecret(), weChatCode);
        // 使用RestTemplate发送请求换取OPENID
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(urlOpenid, String.class);
        // 判断是否成功获取到OPENID
        AssertUtil.isEquals(responseEntity.getStatusCodeValue(), HttpStatus.OK.value(), ErrorCode.MINIPROGRAM_RESULT_OPENID_EMPTY);
        // 判断是否获取OPENID成功
        log.info("小程序-司机端 -Result -{}", responseEntity);
        /*{
            "openid":"xxxxxx",
            "session_key":"xxxxx",
            "unionid":"xxxxx",
            "errcode":0,
            "errmsg":"xxxxx"
        }*/
        return JSON.parseObject(responseEntity.getBody(), MinappOpenIdResult.class);
    }
}
