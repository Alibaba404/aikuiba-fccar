package cn.aikuiba.service.impl;

import cn.aikuiba.pojo.domain.CustomerWallet;
import cn.aikuiba.mapper.CustomerWalletMapper;
import cn.aikuiba.service.ICustomerWalletService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 乘客的钱包 服务实现类
 * </p>
 *
 * @author 蛮小满Sama
 * @since 2023-12-23
 */
@Service
public class CustomerWalletServiceImpl extends ServiceImpl<CustomerWalletMapper, CustomerWallet> implements ICustomerWalletService {

    @Override
    public void addWallet(long customerId) {
        CustomerWallet wallet = new CustomerWallet();
        wallet.setId(customerId);
        wallet.setCreateTime(new Date());
        save(wallet);
    }
}
