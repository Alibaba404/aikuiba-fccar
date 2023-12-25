package cn.aikuiba.service.impl;

import cn.aikuiba.pojo.domain.CustomerSummary;
import cn.aikuiba.mapper.CustomerSummaryMapper;
import cn.aikuiba.service.ICustomerSummaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 乘客数据汇总 服务实现类
 * </p>
 *
 * @author 蛮小满Sama
 * @since 2023-12-23
 */
@Service
public class CustomerSummaryServiceImpl extends ServiceImpl<CustomerSummaryMapper, CustomerSummary> implements ICustomerSummaryService {

    @Override
    public void addSummary(long customerId) {
        CustomerSummary summary = new CustomerSummary();
        summary.setId(customerId);
        save(summary);
    }
}
