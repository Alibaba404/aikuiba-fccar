package cn.aikuiba.controller.app;

import cn.aikuiba.pojo.dto.WechatLoginDTO;
import cn.aikuiba.pojo.vo.LoginInfoVO;
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

@Tag(name = "登录表", description = "登录表")
@RestController
@RequestMapping("/app/login")
public class AppLoginController {

    @Autowired
    public ILoginService loginService;

    @Operation(summary = "小程序登录", description = "小程序登录")
    @Parameter(name = "dto", description = "登录对象", required = true)
    @PostMapping("/wechat")
    public JSONResult<LoginInfoVO> wechatLogin(@RequestBody @Valid WechatLoginDTO dto) {
        return JSONResult.success(loginService.wechatLogin(dto));
    }

}
