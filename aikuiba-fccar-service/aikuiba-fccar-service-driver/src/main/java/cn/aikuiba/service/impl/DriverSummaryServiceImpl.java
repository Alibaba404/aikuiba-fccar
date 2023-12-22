package cn.aikuiba.service.impl;

import cn.aikuiba.pojo.domain.DriverSummary;
import cn.aikuiba.mapper.DriverSummaryMapper;
import cn.aikuiba.service.IDriverSummaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 司机结算对象 服务实现类
 * </p>
 *
 * @author 蛮小满Sama
 * @since 2023-12-19
 */
@Service
public class DriverSummaryServiceImpl extends ServiceImpl<DriverSummaryMapper, DriverSummary> implements IDriverSummaryService {

    @Override
    public void addSummary(long driverId) {
        DriverSummary driverSummary = new DriverSummary();
        driverSummary.setId(driverId);
        driverSummary.setCreateTime(new Date());
        save(driverSummary);
    }
}
