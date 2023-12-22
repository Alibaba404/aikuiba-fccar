package cn.aikuiba.service.impl;

import cn.aikuiba.pojo.domain.DriverWallet;
import cn.aikuiba.mapper.DriverWalletMapper;
import cn.aikuiba.service.IDriverWalletService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 司机的钱包 服务实现类
 * </p>
 *
 * @author 蛮小满Sama
 * @since 2023-12-19
 */
@Service
public class DriverWalletServiceImpl extends ServiceImpl<DriverWalletMapper, DriverWallet> implements IDriverWalletService {

    @Override
    public void addWallet(long driverId) {
        DriverWallet driverWallet = new DriverWallet();
        driverWallet.setId(driverId);
        driverWallet.setCreateTime(new Date());
        driverWallet.setAmount(BigDecimal.ZERO);
        save(driverWallet);
    }
}
