package cn.aikuiba.pojo.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by 蛮小满Sama at 2023/12/21 14:22
 *
 * @description
 */
@ConfigurationProperties(prefix = "fccar.driver")
@Data
public class DriverProperties {
    private Integer rangeDistance;
    private Integer orderDistance;
}
