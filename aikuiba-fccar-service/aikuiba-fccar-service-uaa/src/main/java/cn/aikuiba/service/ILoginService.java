package cn.aikuiba.service;

import cn.aikuiba.pojo.domain.Login;
import cn.aikuiba.pojo.app.dto.MiniProgramLoginDTO;
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

    Boolean create(MiniProgramLoginDTO dto);
}
