package cn.aikuiba.template;

import cn.aikuiba.constants.ErrorCode;
import cn.aikuiba.pojo.result.AccessTokenResult;
import cn.aikuiba.pojo.result.PhoneNumberResult;
import cn.aikuiba.properties.MiniProgramProperties;
import cn.aikuiba.result.MiniProgramOpenIdResult;
import cn.aikuiba.utils.AssertUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 蛮小满Sama at 2023/12/23 11:11
 *
 * @description 微信相关工具类
 */
@Slf4j
public class WeChatTemplate {

    private final MiniProgramProperties miniProgramProperties;
    private final RestTemplate restTemplate;

    private final MiniProgramProperties.URL url;

    public WeChatTemplate(MiniProgramProperties miniProgramProperties, RestTemplate restTemplate) {
        this.miniProgramProperties = miniProgramProperties;
        this.restTemplate = restTemplate;
        this.url = miniProgramProperties.getUrl();
    }

    /**
     * 获取微信OPENID
     *
     * @param weChatCode 微信授权码
     * @return
     */
    public MiniProgramOpenIdResult getOpenid(String weChatCode) {
        String urlOpenid = url.getOpenid();
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
        return JSON.parseObject(responseEntity.getBody(), MiniProgramOpenIdResult.class);
    }

    /**
     * 获取access_token
     * GET https://api.weixin.qq.com/cgi-bin/token
     * grant_type:client_credential
     * appid
     * secret
     */
    public AccessTokenResult getAccessToken() {
        String urlAccessToken = String.format(url.getAccessToken(), miniProgramProperties.getAppId(), miniProgramProperties.getAppSecret());
        ResponseEntity<AccessTokenResult> responseEntity = restTemplate.getForEntity(urlAccessToken, AccessTokenResult.class);
        AssertUtil.isEquals(responseEntity.getStatusCodeValue(), HttpStatus.OK.value(), ErrorCode.APP_ACCESSTOKEN_ERROR);
        return responseEntity.getBody();
    }

    /**
     * 通过手机号授权码获取手机号
     *
     * @param phoneCode 微信手机号码授权码
     * @return
     */
    public PhoneNumberResult getPhoneNumber(String phoneCode) {
        // 获取access_token
        AccessTokenResult accessToken = this.getAccessToken();
        // 使用access_token + phoneCode 获取手机号
        String urlPhoneNumber = String.format(url.getPhoneNumber(), accessToken.getAccess_token());
        // 请求体Map
        Map<String, String> body = new HashMap<>();
        body.put("code", phoneCode);

        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 请求实体类
        HttpEntity<Object> httpEntity = new HttpEntity<>(body, headers);
        ResponseEntity<PhoneNumberResult> responseEntity = restTemplate.postForEntity(urlPhoneNumber, httpEntity, PhoneNumberResult.class);
        AssertUtil.isEquals(responseEntity.getStatusCodeValue(), HttpStatus.OK.value(), ErrorCode.APP_GET_PHONE_ERROR);
        // 返回获取结果
        return responseEntity.getBody();
    }
}
