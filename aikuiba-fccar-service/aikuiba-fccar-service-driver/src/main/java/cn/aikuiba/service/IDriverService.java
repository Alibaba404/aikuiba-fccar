package cn.aikuiba.service;

import cn.aikuiba.pojo.app.dto.MiniProgramRegisterDTO;
import cn.aikuiba.pojo.domain.Driver;
import cn.aikuiba.pojo.vo.DriverVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 司机对象 服务类
 * </p>
 *
 * @author 蛮小满Sama
 * @since 2023-12-19
 */
public interface IDriverService extends IService<Driver> {

    /**
     * 小程序-司机端-司机注册
     *
     * @param miniProgramRegisterDTO 注册对象
     */
    void register(MiniProgramRegisterDTO miniProgramRegisterDTO);

    List<DriverVO> listAll();
}
