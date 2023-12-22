package cn.aikuiba.feign;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * Created by 蛮小满Sama at 2023/12/23 01:24
 *
 * @description
 */
@Component
public class UaaFallback implements FallbackFactory<UaaFeignClientAPI> {
    /**
     * Returns an instance of the fallback appropriate for the given cause.
     *
     * @param cause cause of an exception.
     * @return fallback
     */
    @Override
    public UaaFeignClientAPI create(Throwable cause) {
        return null;
    }
}
