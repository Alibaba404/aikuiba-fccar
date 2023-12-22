package cn.aikuiba.feign;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by 蛮小满Sama at 2023/12/23 01:19
 *
 * @description
 */
@FeignClient(name = "aikuiba-fccar-service-uaa", fallbackFactory = UaaFallback.class)
public interface UaaFeignClientAPI {


}
