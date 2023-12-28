package cn.aikuiba.controller.remote;

import cn.aikuiba.api.remote.api.LoginFeignAPI;
import cn.aikuiba.api.remote.pojo.dto.LoginDto;
import cn.aikuiba.api.remote.pojo.vo.LoginVO;
import cn.aikuiba.constants.Constants;
import cn.aikuiba.constants.ErrorCode;
import cn.aikuiba.pojo.domain.Login;
import cn.aikuiba.result.JSONResult;
import cn.aikuiba.service.ILoginService;
import cn.aikuiba.utils.NameUtil;
import cn.dev33.satoken.annotation.SaIgnore;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by 蛮小满Sama at 2023/12/23 16:44
 *
 * @description
 */

@RestController
public class LoginFeignAPIImpl implements LoginFeignAPI {


    @Autowired
    private ILoginService loginService;


    @SaIgnore   //放行当前请求
    @Override
    public JSONResult<Void> create(LoginDto dto) {
        // 保存登录信息
        // 默认信息: 头像、昵称、创建时间
        Login login = new Login();
        // 对象属性拷贝
        BeanUtils.copyProperties(dto, login);
        login.setAvatar(Constants.Login.LOGIN_AVATAR);
        login.setCreateTime(new Date());
        login.setNickName(NameUtil.getName());
        boolean success = loginService.save(login);
        return success ? JSONResult.success() : JSONResult.error(ErrorCode.APP_SAVE_LOGIN_ERROR);
    }

    @Override
    public JSONResult getLoginByDriverId(Long driverId) {
        LambdaQueryWrapper<Login> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Login::getId, driverId);
        Login login = loginService.getOne(wrapper);
        LoginVO loginVO = new LoginVO();
        loginVO.setId(login.getId());
        loginVO.setNickName(login.getNickName());
        return JSONResult.success(loginVO);
    }
}
