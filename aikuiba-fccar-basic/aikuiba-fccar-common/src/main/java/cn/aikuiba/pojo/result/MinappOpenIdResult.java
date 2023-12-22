package cn.aikuiba.pojo.result;

import lombok.Data;

/**
 * Created by 蛮小满Sama at 2023/12/21 13:49
 *
 * @description 换取OPENID返回对象
 */
@Data
public class MinappOpenIdResult {
    private String openid;
    private String session_key;
    private String unionid;
    private String errcode;
    private String errmsg;
}
