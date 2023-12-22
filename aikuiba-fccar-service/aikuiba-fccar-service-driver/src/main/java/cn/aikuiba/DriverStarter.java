package cn.aikuiba;

import cn.aikuiba.pojo.properties.DriverProperties;
import cn.aikuiba.pojo.properties.MinappProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Created by 蛮小满Sama at 2023/12/19 12:49
 *
 * @description
 */
@EnableConfigurationProperties({MinappProperties.class, DriverProperties.class})  //开启注解读取
@MapperScan("cn.aikuiba.mapper")
@EnableDiscoveryClient
@SpringBootApplication
public class DriverStarter {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(DriverStarter.class, args);
    }
}
