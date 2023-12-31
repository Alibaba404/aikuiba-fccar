package cn.aikuiba.pojo.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * Created by 蛮小满Sama at 2023/12/21 11:09
 *
 * @description 小程序端-注册请求对象
 */
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MiniProgramRegisterDTO {
    @NotBlank(message = "微信登录授权码不能为空")
    private String loginCode;

    @NotBlank(message = "微信手机号授权码不能为空")
    private String phoneCode;
}
