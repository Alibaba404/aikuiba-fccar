package cn.aikuiba.pojo.vo;

import lombok.Data;

import java.util.List;

/**
 * Created by 蛮小满Sama at 2023/12/25 16:45
 *
 * @description 登录成功信息
 */
@Data
public class LoginInfoVO {

    private String satoken;

    private LoginVO login;

    private List<String> permissions;
}
