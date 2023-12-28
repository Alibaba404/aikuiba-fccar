package cn.aikuiba.service;

import cn.aikuiba.pojo.domain.Login;
import cn.aikuiba.pojo.dto.ChangePasswordDTO;
import cn.aikuiba.pojo.dto.WechatLoginDTO;
import cn.aikuiba.pojo.manager.dto.LoginDTO;
import cn.aikuiba.pojo.vo.LoginInfoVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 登录表 服务类
 * </p>
 *
 * @author 蛮小满Sama
 * @since 2023-12-21
 */
public interface ILoginService extends IService<Login> {
    LoginInfoVO wechatLogin(WechatLoginDTO dto);

    LoginInfoVO login(LoginDTO dto);

    /**
     * 管理员初始化
     */
    void initAdmin();

    void logout();

    void updatePassword(ChangePasswordDTO dto);
}
