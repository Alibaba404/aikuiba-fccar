package cn.aikuiba.autoconfigure;

import cn.aikuiba.template.AliOSSTemplate;
import cn.aikuiba.template.MinioOSSTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 蛮小满Sama at 2023/12/23 11:10
 *
 * @description 对象存储服务工具类
 */
@Configuration
public class OSSAutoConfiguration {
    @Bean
    public MinioOSSTemplate minioOSSTemplate() {
        return new MinioOSSTemplate();
    }

    @Bean
    public AliOSSTemplate aliOSSTemplate() {
        return new AliOSSTemplate();
    }
}
