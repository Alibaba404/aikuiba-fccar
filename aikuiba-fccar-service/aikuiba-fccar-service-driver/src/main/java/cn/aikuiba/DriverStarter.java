package cn.aikuiba;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Created by 蛮小满Sama at 2023/12/19 12:49
 *
 * @description
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class DriverStarter {
    public static void main(String[] args) {
        SpringApplication.run(DriverStarter.class, args);
    }
}
