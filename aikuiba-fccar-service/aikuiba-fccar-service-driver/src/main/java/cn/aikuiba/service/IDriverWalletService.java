package cn.aikuiba.service;

import cn.aikuiba.pojo.domain.DriverWallet;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 司机的钱包 服务类
 * </p>
 *
 * @author 蛮小满Sama
 * @since 2023-12-19
 */
public interface IDriverWalletService extends IService<DriverWallet> {

    void addWallet(long driverId);
}
