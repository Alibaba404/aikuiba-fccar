package cn.aikuiba.pojo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by 蛮小满Sama at 2023/12/27 09:35
 *
 * @description
 */
@Accessors(chain = true)
@Data
public class LoginVO {

    private String username;

    private String nickName;

    private String avatar;

    private String phone;

    /*是否管理员*/
    private Boolean admin;


}
