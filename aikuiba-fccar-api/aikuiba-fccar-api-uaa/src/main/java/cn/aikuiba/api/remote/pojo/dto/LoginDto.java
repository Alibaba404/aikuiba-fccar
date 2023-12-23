package cn.aikuiba.api.remote.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * Created by 蛮小满Sama at 2023/12/23 16:17
 *
 * @description
 */
@Accessors(chain = true)
@Data
public class LoginDto {

    @NotNull(message = "ID不能为空")
    private Long id;

    private String username;

    private String password;

    private String phone;

    private String openId;

    // 0:平台,1:司机,2:乘客
    @NotNull(message = "TYPE不能为空")
    private Integer type;
}
