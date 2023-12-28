package cn.aikuiba;

import cn.aikuiba.service.ILoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by 蛮小满Sama at 2023/12/28 13:07
 *
 * @description 应用启动监听器
 */
@Slf4j
@Component
public class UaaAppListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private ILoginService loginService;

    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loginService.initAdmin();
        log.info("管理员账户初始化完成...「{}」", this.getClass().getSimpleName());
    }
}
