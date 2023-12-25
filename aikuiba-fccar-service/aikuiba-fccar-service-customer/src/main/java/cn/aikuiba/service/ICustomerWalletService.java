package cn.aikuiba.service;

import cn.aikuiba.pojo.domain.CustomerWallet;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 乘客的钱包 服务类
 * </p>
 *
 * @author 蛮小满Sama
 * @since 2023-12-23
 */
public interface ICustomerWalletService extends IService<CustomerWallet> {

    void addWallet(long customerId);
}
