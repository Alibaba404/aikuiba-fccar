package cn.aikuiba.service.impl;

import cn.aikuiba.pojo.domain.DriverSetting;
import cn.aikuiba.mapper.DriverSettingMapper;
import cn.aikuiba.pojo.properties.DriverProperties;
import cn.aikuiba.service.IDriverSettingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 司机配置 服务实现类
 * </p>
 *
 * @author 蛮小满Sama
 * @since 2023-12-19
 */
@Service
public class DriverSettingServiceImpl extends ServiceImpl<DriverSettingMapper, DriverSetting> implements IDriverSettingService {

    @Autowired
    private DriverProperties driverProperties;

    @Override
    public void addSetting(long driverId) {
        DriverSetting driverSetting = new DriverSetting();
        driverSetting.setAutoAccept(true);
        driverSetting.setListenService(true);
        driverSetting.setCreateTime(new Date());
        driverSetting.setOrientation(true);
        driverSetting.setId(driverId); //这里使用司机ID作为配置ID是为了方便后面查询
        driverSetting.setRangeDistance(driverProperties.getRangeDistance());
        driverSetting.setOrderDistance(driverProperties.getOrderDistance());
        save(driverSetting);
    }
}
