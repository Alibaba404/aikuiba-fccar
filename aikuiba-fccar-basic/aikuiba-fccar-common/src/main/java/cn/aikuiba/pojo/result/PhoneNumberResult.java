package cn.aikuiba.pojo.result;

import lombok.Data;

/**
 * Created by 蛮小满Sama at 2023/12/25 13:20
 *
 * @description 获取手机号的返回结果对象
 *     {
 *         "errcode":0,
 *         "errmsg":"ok",
 *         "phone_info": {
 *             "phoneNumber":"xxxxxx",
 *             "purePhoneNumber": "xxxxxx",
 *             "countryCode": 86,
 *             "watermark": {
 *                 "timestamp": 1637744274,
 *                 "appid": "xxxx"
 *             }
 *         }
 *     }
 */
@Data
public class PhoneNumberResult {

    private Integer errcode;
    private String errmsg;
    private PhoneInfo phone_info;

    @Data
    public class PhoneInfo {
        // 用户绑定的手机号（国外手机号会有区号）
        private String phoneNumber;
        // 没有区号的手机号
        private String purePhoneNumber;
        // 区号
        private String countryCode;
    }

}
