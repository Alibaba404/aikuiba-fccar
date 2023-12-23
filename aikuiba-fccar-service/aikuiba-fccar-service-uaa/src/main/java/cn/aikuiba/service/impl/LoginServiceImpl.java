package cn.aikuiba.service.impl;

import cn.aikuiba.pojo.domain.Login;
import cn.aikuiba.mapper.LoginMapper;
import cn.aikuiba.pojo.app.dto.MiniProgramLoginDTO;
import cn.aikuiba.service.ILoginService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 登录表 服务实现类
 * </p>
 *
 * @author 蛮小满Sama
 * @since 2023-12-21
 */
@Service
public class LoginServiceImpl extends ServiceImpl<LoginMapper, Login> implements ILoginService {

    @Override
    public Boolean create(MiniProgramLoginDTO dto) {
        // 这里是否需要判断openid已经存在? TODO 待讨论
        Login login = new Login();
        login.setCreateTime(new Date());
        login.setId(dto.getId());
        login.setOpenId(dto.getOpenId());
        login.setType(dto.getType());
        save(login);
        return Boolean.TRUE;
    }
}
