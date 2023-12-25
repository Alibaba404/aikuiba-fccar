package cn.aikuiba.service;

import cn.aikuiba.pojo.domain.CustomerSummary;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 乘客数据汇总 服务类
 * </p>
 *
 * @author 蛮小满Sama
 * @since 2023-12-23
 */
public interface ICustomerSummaryService extends IService<CustomerSummary> {

    void addSummary(long customerId);
}
