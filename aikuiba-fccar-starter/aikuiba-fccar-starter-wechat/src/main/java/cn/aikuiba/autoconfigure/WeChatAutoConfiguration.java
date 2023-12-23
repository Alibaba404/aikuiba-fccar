package cn.aikuiba.autoconfigure;

import cn.aikuiba.properties.MiniProgramProperties;
import cn.aikuiba.template.WeChatTemplate;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.client.RestTemplate;

/**
 * Created by 蛮小满Sama at 2023/12/23 11:10
 *
 * @description
 */
@EnableConfigurationProperties(MiniProgramProperties.class)
@Configuration
public class WeChatAutoConfiguration {

    @Bean
    public WeChatTemplate weChatTemplate(MiniProgramProperties miniProgramProperties, RestTemplate restTemplate) {
        return new WeChatTemplate(miniProgramProperties, restTemplate);
    }


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
