package cn.aikuiba.service;

import cn.aikuiba.pojo.app.dto.MiniProgramRegisterDTO;
import cn.aikuiba.pojo.domain.Customer;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 客户 服务类
 * </p>
 *
 * @author 蛮小满Sama
 * @since 2023-12-23
 */
public interface ICustomerService extends IService<Customer> {

    void register(MiniProgramRegisterDTO dto);
}
