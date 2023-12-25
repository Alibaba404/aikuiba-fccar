package cn.aikuiba.service.impl;

import cn.aikuiba.pojo.domain.CustomerCar;
import cn.aikuiba.mapper.CustomerCarMapper;
import cn.aikuiba.service.ICustomerCarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户车辆 服务实现类
 * </p>
 *
 * @author 蛮小满Sama
 * @since 2023-12-23
 */
@Service
public class CustomerCarServiceImpl extends ServiceImpl<CustomerCarMapper, CustomerCar> implements ICustomerCarService {

}
