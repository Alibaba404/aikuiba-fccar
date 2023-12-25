package cn.aikuiba.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 蛮小满Sama at 2023/12/21 13:31
 *
 * @description 微信小程序的配置类
 */
@ConfigurationProperties(prefix = "fccar.minapp")
@Data
public class MiniProgramProperties {
    private String appId;
    private String appSecret;
//    private String urlOpenid;
//    private String urlAccessToken;
//    private String urlPhoneNumber;
    // 多层级,必须new出来不然就可能null
    private URL url = new URL();

    @Data
    public class URL {
        private String openid;
        private String accessToken;
        private String phoneNumber;
    }
}
