package cn.aikuiba.pojo.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Created by 蛮小满Sama at 2023/12/23 01:31
 *
 * @description
 */
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MiniProgramLoginDTO {
    private Long id;
    //0是平台账户，1是司机账户，2是客户账户
    private Integer type;
    // 微信ID
    private String openId;

}
