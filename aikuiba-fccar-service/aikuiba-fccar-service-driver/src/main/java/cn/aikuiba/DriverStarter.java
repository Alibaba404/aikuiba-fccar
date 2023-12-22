package cn.aikuiba;

import cn.aikuiba.pojo.properties.MiniProgramProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Created by 蛮小满Sama at 2023/12/19 12:49
 *
 * @description
 */
@EnableFeignClients
@EnableConfigurationProperties({MiniProgramProperties.class})  //开启注解读取,可以在获取配置的Properties中使用@Component替代
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
