package cn.aikuiba.pojo.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * Created by 蛮小满Sama at 2023/12/23 17:39
 *
 * @description
 */
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MiniProgramCustomerRegisterDTO {
    @NotBlank(message = "授权码不能为空")
    private String code;
}
