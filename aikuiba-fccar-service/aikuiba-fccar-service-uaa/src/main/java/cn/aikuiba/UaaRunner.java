package cn.aikuiba;

import cn.aikuiba.constants.Constants;
import cn.aikuiba.mapper.LoginMapper;
import cn.aikuiba.pojo.domain.Login;
import cn.aikuiba.service.ILoginService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by 蛮小满Sama at 2023/12/28 10:31
 *
 * @description 实现项目启动运行方式一 ,比监听器晚执行
 */
@Slf4j
@Component
public class UaaRunner implements ApplicationRunner {

    @Autowired
    private ILoginService loginService;

    /**
     * 初始化管理员用户
     * 1.判断是否已存在超级管理员
     * 2.不存在则新增
     * 2.1 账户默认信息
     * 2.3 密码加密
     * Callback used to run the bean.
     *
     * @param args incoming application arguments
     */
    @Override
    public void run(ApplicationArguments args) {
        loginService.initAdmin();
        log.info("管理员账户初始化完成...「{}」", this.getClass().getSimpleName());
    }
}
