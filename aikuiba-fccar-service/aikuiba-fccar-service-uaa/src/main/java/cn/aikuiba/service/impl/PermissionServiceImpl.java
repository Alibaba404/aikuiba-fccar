package cn.aikuiba.service.impl;

import cn.aikuiba.pojo.domain.Permission;
import cn.aikuiba.mapper.PermissionMapper;
import cn.aikuiba.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author 蛮小满Sama
 * @since 2023-12-21
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
