package cn.aikuiba.config;

import cn.aikuiba.constants.Constants;
import cn.dev33.satoken.stp.StpInterface;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 自定义权限加载接口实现类
 */
public class StpInterfaceImpl implements StpInterface {

    private RedisTemplate<String, Object> redisTemplate;

    public StpInterfaceImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        String KEY_PERMISSION = String.format(Constants.Redis.KEY_PERMISSION, loginId);
        List<String> loginPermission = (List<String>) redisTemplate.opsForValue().get(KEY_PERMISSION);
        if (null == loginPermission) loginPermission = Collections.emptyList();
        return loginPermission;
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 本 list 仅做模拟，实际项目中要根据具体业务逻辑来查询角色
        List<String> list = new ArrayList<String>();
        return list;
    }

}
