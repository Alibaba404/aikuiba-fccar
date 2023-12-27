package cn.aikuiba.pojo.manager.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Created by 蛮小满Sama at 2023/12/27 09:17
 *
 * @description
 */
@Data
public class LoginDTO {
    @NotEmpty(message = "账号不能为空")
    private String username;
    @NotEmpty(message = "密码不能为空")
    private String password;
}
