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
        public static final String KEY_ACCESS = "minioadmin";
        public static final String KEY_SECRET = "minioadmin123?";
        public static final String ADDRESS = "http://47.109.72.16:9090";
        public static final String BUCKET = "aikuiba-fccar";
    }
}
