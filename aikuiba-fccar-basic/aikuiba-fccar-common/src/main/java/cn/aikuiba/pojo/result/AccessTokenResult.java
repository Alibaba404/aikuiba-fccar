package cn.aikuiba.pojo.result;

import lombok.Data;

/**
 * Created by 蛮小满Sama at 2023/12/25 11:19
 *
 * @description
 */
@Data
public class AccessTokenResult {
    private String access_token;

    //expires_in	number	凭证有效时间，单位：秒。目前是7200秒之内的值。
    private Integer expires_in;
}
