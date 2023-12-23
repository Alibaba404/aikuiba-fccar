package cn.aikuiba.controller.app;

import cn.aikuiba.pojo.app.dto.MiniProgramLoginDTO;
import cn.aikuiba.result.JSONResult;
import cn.aikuiba.service.ILoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by 蛮小满Sama at 2023/12/23 01:28
 *
 * @description 小程序-登录信息API
 */
@Tag(name = "小程序-登录信息API", description = "小程序-登录信息API")
@RequestMapping("/app/login")
@RestController
public class MiniProgramLoginController {

    @Autowired
    public ILoginService loginService;

    @Operation(summary = "保存Login", description = "基础对象保存接口")
    @Parameter(name = "login", description = "保存的对象", required = true)
    @PostMapping
    public JSONResult create(@RequestBody @Valid MiniProgramLoginDTO dto) {
        return JSONResult.success(loginService.create(dto));
    }

}
