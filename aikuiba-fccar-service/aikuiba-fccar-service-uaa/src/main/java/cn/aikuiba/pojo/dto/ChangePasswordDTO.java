package cn.aikuiba.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Created by 蛮小满Sama at 2023/12/28 16:12
 *
 * @description
 */
@Data
public class ChangePasswordDTO {

    /*旧密码*/
    @NotEmpty(message = "旧密码不能为空")
    private String oldPassword;

    /*新密码*/
    @NotEmpty(message = "新密码不能为空")
    private String newPassword;
}
