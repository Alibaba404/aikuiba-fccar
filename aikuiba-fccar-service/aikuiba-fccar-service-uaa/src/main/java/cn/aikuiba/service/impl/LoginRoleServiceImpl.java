package cn.aikuiba.service.impl;

import cn.aikuiba.pojo.domain.LoginRole;
import cn.aikuiba.mapper.LoginRoleMapper;
import cn.aikuiba.service.ILoginRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户和角色中间表 服务实现类
 * </p>
 *
 * @author 蛮小满Sama
 * @since 2023-12-21
 */
@Service
public class LoginRoleServiceImpl extends ServiceImpl<LoginRoleMapper, LoginRole> implements ILoginRoleService {

}
