package cn.aikuiba.pojo.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by 蛮小满Sama at 2023/12/21 14:22
 *
 * @description
 */
@Component
@ConfigurationProperties(prefix = "fccar.driver")
@Data
public class DriverProperties {
    private Integer rangeDistance;
    private Integer orderDistance;
}
