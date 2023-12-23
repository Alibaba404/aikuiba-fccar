package cn.aikuiba.feign;

import cn.aikuiba.pojo.app.dto.MiniProgramLoginDTO;
import cn.aikuiba.result.JSONResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * Created by 蛮小满Sama at 2023/12/23 01:19
 *
 * @description
 */
@FeignClient(name = "aikuiba-fccar-service-uaa", fallbackFactory = UaaFallback.class)
public interface UaaFeignClientAPI {

    @PostMapping("/drive/uaa/app/login")
    JSONResult create(@RequestBody @Valid MiniProgramLoginDTO dto);

}
