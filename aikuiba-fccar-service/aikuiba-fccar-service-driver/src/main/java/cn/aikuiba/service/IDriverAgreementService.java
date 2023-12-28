package cn.aikuiba.service;

import cn.aikuiba.pojo.domain.DriverAgreement;
import cn.aikuiba.pojo.query.PageQueryWrapper;
import cn.aikuiba.result.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 司机的协议签署 服务类
 * </p>
 *
 * @author 蛮小满Sama
 * @since 2023-12-19
 */
public interface IDriverAgreementService extends IService<DriverAgreement> {

    PageResult<DriverAgreement> pageData(PageQueryWrapper<DriverAgreement> query);
}
