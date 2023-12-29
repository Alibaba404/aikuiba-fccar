package cn.aikuiba.constants;


//常量维护
public class Constants {

    //手机号正则表达式
    public static final String CHINA_PHONE_REGEX = "^((13[0-9])|(14[0,1,4-9])|(15[0-3,5-9])|(16[2,5,6,7])|(17[0-8])|(18[0-9])|(19[0-3,5-9]))\\d{8}$";

    //编码
    public static final String UTF_8 = "UTF-8";

    //登录相关
    public class Login {
        // 头像地址
        public static final String LOGIN_AVATAR = "http://files.aikuiba.cn:29000/d/G-AlistFiles/Share/Picture/%E4%B8%80%E4%BA%8C%E5%B8%83%E5%B8%83/016.jpeg";
        public static final int TYPE_ADMIN = 0;     //平台管理
        public static final int TYPE_DRIVER = 1;    //司机
        public static final int TYPE_CUSTOMER = 2; // 乘客
        public static final String ADMIN_DEFAULT_USERNAME = "admin"; //管理员默认名称
        public static final String ADMIN_DEFAULT_PASSWORD = "123456"; //管理员默认密码
    }

    //远程调用
    public class Remote {
        //服务名
        public static final String SERVICE_UAA = "aikuiba-fccar-service-uaa";
        public static final String PREFIX_LOGIN_REMOTE = "/login/remote";
    }

    /**
     * Redis 相关常量
     */
    public class Redis {
        public static final String KEY_PERMISSION = "LOGIN_PERMISSION_%s";
    }

    public class Minio {
        public static final String KEY_ACCESS = "aikuiba";
        public static final String KEY_SECRET = "aikuiba2023.?";
        public static final String ADDRESS = "http://files.aikuiba.cn:9090/";
        public static final String BUCKET_NAME = "aikuiba-fccar";
    }

    /**
     * 阿里云
     */
    public class Ali {
        public static final String OSS_REGION = "oss-cn-chengdu.aliyuncs.com";
        public static final String OSS_ACCESS_KEY_ID = "LTAI5tNKcqfuX4HeT9pCQDYQ";
        public static final String OSS_ACCESS_KEY_SECRET = "CNgSe7lKl38iH7jaljLgJvpUT6nOvQ";
        public static final String OSS_ENDPOINT_URL = "http://" + OSS_REGION;
        public static final String OSS_BUCKET_NAME = "aikuiba-fccar";
        public static final String OSS_BASE_URL = String.format("https://%s.%s/", OSS_BUCKET_NAME, OSS_REGION);
    }

    public class Driver {
        //签署状态：0未生成，1已生成，2已寄出，3成功签署，4签署失败
        public static final Integer AGREEMENT_SIGNED_STATUS_NO = 0;
        public static final Integer AGREEMENT_SIGNED_STATUS_CREATED = 1;
        public static final Integer AGREEMENT_SIGNED_STATUS_SEND = 2;
        public static final Integer AGREEMENT_SIGNED_STATUS_SIGNED = 3;
        public static final Integer AGREEMENT_SIGNED_STATUS_SIGNED_FAILED = 4;
        // 合同状态:0-有效,1-作废
        public static final Integer AGREEMENT_STATUS_SUCCESS = 0;
        public static final Integer AGREEMENT_STATUS_DISABLED = 1;
    }
}
