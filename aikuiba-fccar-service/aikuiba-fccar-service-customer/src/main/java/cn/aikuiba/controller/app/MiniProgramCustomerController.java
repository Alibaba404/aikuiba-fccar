package cn.aikuiba.controller.app;

import cn.aikuiba.pojo.app.dto.MiniProgramCustomerRegisterDTO;
import cn.aikuiba.result.JSONResult;
import cn.aikuiba.service.ICustomerService;
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
@Tag(name = "小程序-乘客API", description = "接收来自微信小程序端乘客端的请求")
@RequestMapping("/app/customer")
@RestController
public class MiniProgramCustomerController {

    @Autowired
    public ICustomerService customerService;

    @Operation(summary = "乘客注册", description = "基础对象保存接口")
    @Parameter(name = "dto", description = "乘客注册请求对象", required = true)
    @PostMapping("/register")
    public JSONResult<Boolean> register(@RequestBody @Valid MiniProgramCustomerRegisterDTO dto) {
        log.info("CODE:" + dto.getCode());
        customerService.register(dto);
        return JSONResult.success();
    }
}
