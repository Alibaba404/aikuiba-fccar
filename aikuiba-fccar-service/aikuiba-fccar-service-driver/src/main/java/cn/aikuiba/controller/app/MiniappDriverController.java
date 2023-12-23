package cn.aikuiba.controller.app;

import cn.aikuiba.pojo.app.dto.MiniProgramDriverRegisterDTO;
import cn.aikuiba.result.JSONResult;
import cn.aikuiba.service.IDriverService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by 蛮小满Sama at 2023/12/21 10:47
 *
 * @description
 */
@Slf4j
@Tag(name = "小程序-司机API", description = "接收来自微信小程序端司机端的请求")
@RequestMapping("/app/driver")
@RestController
public class MiniappDriverController {

    @Autowired
    public IDriverService driverService;

    @Operation(summary = "司机注册", description = "基础对象保存接口")
    @Parameter(name = "minappDriverRegisterDTO", description = "司机注册请求对象", required = true)
    @PostMapping("/register")
    public JSONResult<Boolean> register(@RequestBody @Valid MiniProgramDriverRegisterDTO minappDriverRegisterDTO) {
        log.info("CODE:" + minappDriverRegisterDTO.getCode());
        driverService.register(minappDriverRegisterDTO);
        return JSONResult.success();
    }
}
