package cn.aikuiba.feign;

import cn.aikuiba.constants.ErrorCode;
import cn.aikuiba.pojo.app.dto.MiniProgramLoginDTO;
import cn.aikuiba.result.JSONResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * Created by 蛮小满Sama at 2023/12/23 01:24
 *
 * @description
 */
@Slf4j
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
        return new UaaFeignClientAPI() {
            @Override
            public JSONResult create(MiniProgramLoginDTO dto) {
                cause.printStackTrace();
                log.info("哦豁,触发降级了! - DTO {}", dto);
                return JSONResult.error(ErrorCode.SERVICE_REQUEST_ERROR);
            }
        };
    }
}
