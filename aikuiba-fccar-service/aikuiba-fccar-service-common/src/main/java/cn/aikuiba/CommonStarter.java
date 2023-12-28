package cn.aikuiba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by 蛮小满Sama at 2023/12/27 16:12
 *
 * @description 公共服务
 */
@EnableDiscoveryClient
@SpringBootApplication
public class CommonStarter {
    public static void main(String[] args) {
        SpringApplication.run(CommonStarter.class, args);
    }
}
