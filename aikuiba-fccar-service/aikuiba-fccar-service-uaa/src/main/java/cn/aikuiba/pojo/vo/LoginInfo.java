package cn.aikuiba.pojo.vo;

import lombok.Data;

/**
 * Created by 蛮小满Sama at 2023/12/25 16:45
 *
 * @description 登录成功信息
 */
@Data
public class LoginInfo {

    private String satoken;

    private String avatar;

    private String nickName;
}
