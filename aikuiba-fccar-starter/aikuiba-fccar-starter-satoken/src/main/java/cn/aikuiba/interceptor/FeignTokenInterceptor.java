package cn.aikuiba.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Feign 请求的拦截器,拦截Feign请求并为其添加请求头数据
 */
@Slf4j
@Component
public class FeignTokenInterceptor implements RequestInterceptor {

    @Value("${sa-token.token-name}")
    private String tokenName;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        // 请求属性对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // 获取请求头
        String tokenValue = requestAttributes.getRequest().getHeader(tokenName);
        // 存储到Feign生成的请求模版中的请求头
        requestTemplate.header(tokenName, tokenValue);
    }
}
