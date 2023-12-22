package cn.aikuiba.pojo.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by 蛮小满Sama at 2023/12/21 13:31
 *
 * @description
 */
@ConfigurationProperties(prefix = "fccar.minapp")
@Data
public class MinappProperties {
    private String appId;
    private String appSecret;
    private String urlOpenid;
}
