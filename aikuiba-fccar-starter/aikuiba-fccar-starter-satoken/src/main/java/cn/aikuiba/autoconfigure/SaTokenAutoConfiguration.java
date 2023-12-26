package cn.aikuiba.autoconfigure;

import cn.aikuiba.config.RedisConfig;
import cn.aikuiba.config.SaTokenConfigure;
import cn.aikuiba.config.StpInterfaceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by 蛮小满Sama at 2023/12/26 17:29
 *
 * @description
 */
@Configuration
@Import({RedisConfig.class, SaTokenConfigure.class})
public class SaTokenAutoConfiguration {

    @Bean
    public StpInterfaceImpl stpInterfaceImpl(RedisTemplate<String, Object> redisTemplate) {
        return new StpInterfaceImpl(redisTemplate);
    }
}
