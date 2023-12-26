package cn.aikuiba.api.remote.fallback;

import cn.aikuiba.api.remote.api.LoginFeignAPI;
import cn.aikuiba.api.remote.pojo.dto.LoginDto;
import cn.aikuiba.constants.ErrorCode;
import cn.aikuiba.result.JSONResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * Created by 蛮小满Sama at 2023/12/23 16:15
 *
 * @description 登录信息降级类
 */
@Slf4j
@Component
public class LoginAPIFeignFallbackFactory implements FallbackFactory<LoginFeignAPI> {
    /**
     * Returns an instance of the fallback appropriate for the given cause.
     *
     * @param cause cause of an exception.
     * @return fallback
     */
    @Override
    public LoginFeignAPI create(Throwable cause) {
        return new LoginFeignAPI() {
            @Override
            public JSONResult create(LoginDto dto) {
                cause.printStackTrace();
                log.info("远程登录调用 --降级-- {}", dto);
                return JSONResult.error(ErrorCode.APP_SAVE_LOGIN_ERROR);
            }
        };
    }
}
