package cn.aikuiba;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by 蛮小满Sama at 2023/12/21 15:09
 *
 * @description
 */
@EnableDiscoveryClient
@SpringBootApplication
public class UaaStarter {
    public static void main(String[] args) {
        SpringApplication.run(UaaStarter.class, args);
    }
}
