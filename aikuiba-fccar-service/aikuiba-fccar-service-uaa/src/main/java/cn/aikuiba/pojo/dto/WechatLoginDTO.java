package cn.aikuiba.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created by 蛮小满Sama at 2023/12/25 16:15
 *
 * @description
 */
@Data
public class WechatLoginDTO {
    // 微信登录授权码
    @NotEmpty(message = "微信登录授权码不能为空")
    private String wxCode;

    // 登录用户类型
    @NotNull(message = "登录用户类型不能为空")
    private Integer loginType;
}
