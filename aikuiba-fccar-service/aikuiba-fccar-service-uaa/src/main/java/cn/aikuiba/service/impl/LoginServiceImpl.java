package cn.aikuiba.service.impl;

import cn.aikuiba.constants.Constants;
import cn.aikuiba.constants.ErrorCode;
import cn.aikuiba.mapper.LoginMapper;
import cn.aikuiba.mapper.PermissionMapper;
import cn.aikuiba.pojo.domain.Login;
import cn.aikuiba.pojo.dto.WechatLoginDTO;
import cn.aikuiba.pojo.manager.dto.LoginDTO;
import cn.aikuiba.pojo.vo.LoginInfoVO;
import cn.aikuiba.pojo.vo.LoginVO;
import cn.aikuiba.result.MiniProgramOpenIdResult;
import cn.aikuiba.service.ILoginService;
import cn.aikuiba.template.WeChatTemplate;
import cn.aikuiba.utils.AssertUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 登录表 服务实现类
 * </p>
 *
 * @author 蛮小满Sama
 * @since 2023-12-21
 */
@Slf4j
@Service
public class LoginServiceImpl extends ServiceImpl<LoginMapper, Login> implements ILoginService {

    @Autowired
    private WeChatTemplate weChatTemplate;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 用户统一登录方法
     * 1.获取微信登录授权code
     * 2.通过code获取openid(注入wechatTemplate)
     * 3.通过openid+type查询是否已注册
     * 4.保存token信息
     * 5.返回token
     *
     * @param dto
     * @return
     */
    @Override
    public LoginInfoVO wechatLogin(WechatLoginDTO dto) {
        //  1.获取微信登录授权code
        String wxCode = dto.getWxCode();
        Integer loginType = dto.getLoginType();
        //  2.通过code获取openid(注入wechatTemplate)
        MiniProgramOpenIdResult openIdResult = weChatTemplate.getOpenid(wxCode);
        AssertUtil.isNotEmpty(openIdResult.getOpenid(), ErrorCode.APP_GET_OPENID_ERROR);
        //  3.通过openid+type查询是否已注册
        LambdaQueryWrapper<Login> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Login::getOpenId, openIdResult.getOpenid());
        wrapper.eq(Login::getType, loginType);
        Login login = getOne(wrapper);
        AssertUtil.isNotNull(login, ErrorCode.APP_NOT_REGISTER);
        return getLoginInfo(login);
    }

    /**
     * 抽取获取登录信息的公共方法
     *
     * @param login
     * @return
     */
    private LoginInfoVO getLoginInfo(Login login) {
        // 3.是否禁用
        AssertUtil.isTrue(login.getEnabled(), ErrorCode.USER_ACCOUNT_DISABLED);
        //  4.保存token信息
        StpUtil.login(login.getId());
        // 获取当前用户的权限数据,并保存到Redis
        List<String> permissions = permissionMapper.listPermissionByLoginId(login.getId());
        String KEY_PERMISSION = String.format(Constants.Redis.KEY_PERMISSION, login.getId());
        redisTemplate.opsForValue().set(KEY_PERMISSION, permissions);
        // 获取当前会话的 token 信息参数
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        log.info("Get tokenInfo :{}", tokenInfo);
        //  5.返回token和其他信息,头像,昵称等
        // 里层信息
        LoginVO loginVO = new LoginVO()
                .setUsername(login.getUsername())
                .setNickName(login.getNickName())
                .setAvatar(login.getAvatar())
                .setPhone(login.getPhone())
                .setAdmin(login.getAdmin());
        LoginInfoVO loginInfo = new LoginInfoVO();
        loginInfo.setSatoken(tokenInfo.getTokenValue());
        loginInfo.setLogin(loginVO);
        loginInfo.setPermissions(permissions);
        return loginInfo;
    }

    /**
     * 管理端登录
     *
     * @param dto
     * @return
     */
    @Override
    public LoginInfoVO login(LoginDTO dto) {
        String username = dto.getUsername();
        String password = dto.getPassword();
        LambdaQueryWrapper<Login> wrapper = new LambdaQueryWrapper<Login>()
                .eq(Login::getUsername, username);
        Login login = getOne(wrapper);
        AssertUtil.isNotNull(login, ErrorCode.USER_USERNAME_OR_PASSWORD_ERROR);
        // 1.参数校验(已使用注解)
        // 2.校验密码
        boolean matches = bCryptPasswordEncoder.matches(password, login.getPassword());
        AssertUtil.isTrue(matches, ErrorCode.USER_USERNAME_OR_PASSWORD_ERROR);
        return getLoginInfo(login);
    }

    /**
     * 初始化管理员用户
     * 1.判断是否已存在超级管理员
     * 2.不存在则新增
     * 2.1 账户默认信息
     * 2.3 密码加密
     */
    @Override
    public void initAdmin() {
        LambdaQueryWrapper<Login> wrapper = new LambdaQueryWrapper<Login>()
                .eq(Login::getUsername, "admin")
                .eq(Login::getAdmin, Boolean.TRUE);
        Login login = this.getOne(wrapper);
        if (login == null) {
            login = new Login();
            login.setUsername(Constants.Login.ADMIN_DEFAULT_USERNAME);
            login.setPassword(bCryptPasswordEncoder.encode(Constants.Login.ADMIN_DEFAULT_PASSWORD));
            login.setType(Constants.Login.TYPE_ADMIN);
            login.setAvatar(Constants.Login.LOGIN_AVATAR);
            login.setAdmin(Boolean.TRUE);
            login.setNickName(Constants.Login.ADMIN_DEFAULT_USERNAME);
            login.setName(Constants.Login.ADMIN_DEFAULT_USERNAME);
            login.setCreateTime(new Date());
            save(login);
            log.info("Uaa--管理员数据初始化完成----「{}」", login);
        }
    }
}
