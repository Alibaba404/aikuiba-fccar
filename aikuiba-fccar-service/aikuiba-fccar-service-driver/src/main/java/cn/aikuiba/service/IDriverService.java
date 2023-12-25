package cn.aikuiba.service;

import cn.aikuiba.pojo.app.dto.MiniProgramRegisterDTO;
import cn.aikuiba.pojo.domain.Driver;
import com.baomidou.mybatisplus.extension.service.IService;

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
     * @param minappDriverRegisterDTO 注册对象
     */
    void register(MiniProgramRegisterDTO minappDriverRegisterDTO);
}
