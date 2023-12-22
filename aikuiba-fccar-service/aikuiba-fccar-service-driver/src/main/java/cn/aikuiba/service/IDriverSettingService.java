package cn.aikuiba.service;

import cn.aikuiba.pojo.domain.DriverSetting;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 司机配置 服务类
 * </p>
 *
 * @author 蛮小满Sama
 * @since 2023-12-19
 */
public interface IDriverSettingService extends IService<DriverSetting> {

    void addSetting(long driverId);

}
