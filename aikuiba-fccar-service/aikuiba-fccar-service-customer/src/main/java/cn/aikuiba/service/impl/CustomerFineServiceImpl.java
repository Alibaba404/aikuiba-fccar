package cn.aikuiba.service.impl;

import cn.aikuiba.pojo.domain.CustomerFine;
import cn.aikuiba.mapper.CustomerFineMapper;
import cn.aikuiba.service.ICustomerFineService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 乘客罚款 服务实现类
 * </p>
 *
 * @author 蛮小满Sama
 * @since 2023-12-23
 */
@Service
public class CustomerFineServiceImpl extends ServiceImpl<CustomerFineMapper, CustomerFine> implements ICustomerFineService {

}
