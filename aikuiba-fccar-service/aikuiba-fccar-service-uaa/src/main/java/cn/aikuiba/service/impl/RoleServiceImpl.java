package cn.aikuiba.service.impl;

import cn.aikuiba.pojo.domain.Role;
import cn.aikuiba.mapper.RoleMapper;
import cn.aikuiba.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author 蛮小满Sama
 * @since 2023-12-21
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
