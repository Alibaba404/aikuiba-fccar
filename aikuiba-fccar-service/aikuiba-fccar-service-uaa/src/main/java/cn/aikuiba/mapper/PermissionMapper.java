package cn.aikuiba.mapper;

import cn.aikuiba.pojo.domain.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author 蛮小满Sama
 * @since 2023-12-21
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    List<String> listPermissionByLoginId(Long loginId);
}
