package cn.aikuiba.api.remote.api;

import cn.aikuiba.api.remote.fallback.LoginAPIFeignFallbackFactory;
import cn.aikuiba.api.remote.pojo.dto.LoginDto;
import cn.aikuiba.constants.Constants;
import cn.aikuiba.result.JSONResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * Created by 蛮小满Sama at 2023/12/23 16:14
 *
 * @description
 */

@FeignClient(value = Constants.Remote.SERVICE_UAA,
        fallbackFactory = LoginAPIFeignFallbackFactory.class)
public interface LoginFeignAPI {

    /**
     * 保存登录信息
     * ⚠️注意:这里的地址好像没啥用,服务之间调用不会走网关
     *
     * @param dto
     * @return
     */
    @PostMapping("/login/create")
    JSONResult create(@RequestBody @Valid LoginDto dto);

}
