package cn.aikuiba.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

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
    private String urlOpenid;
}
