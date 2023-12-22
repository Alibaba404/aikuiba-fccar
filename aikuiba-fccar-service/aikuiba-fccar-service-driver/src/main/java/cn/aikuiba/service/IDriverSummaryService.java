package cn.aikuiba.service;

import cn.aikuiba.pojo.domain.DriverSummary;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 司机结算对象 服务类
 * </p>
 *
 * @author 蛮小满Sama
 * @since 2023-12-19
 */
public interface IDriverSummaryService extends IService<DriverSummary> {

    void addSummary(long driverId);
}
