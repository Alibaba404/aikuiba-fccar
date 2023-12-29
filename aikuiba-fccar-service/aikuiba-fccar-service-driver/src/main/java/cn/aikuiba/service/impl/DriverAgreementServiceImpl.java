package cn.aikuiba.service.impl;

import cn.aikuiba.constants.Constants;
import cn.aikuiba.constants.ErrorCode;
import cn.aikuiba.pojo.domain.DriverAgreement;
import cn.aikuiba.mapper.DriverAgreementMapper;
import cn.aikuiba.pojo.dto.UploadAgreementDTO;
import cn.aikuiba.pojo.query.PageQueryWrapper;
import cn.aikuiba.result.PageResult;
import cn.aikuiba.service.IDriverAgreementService;
import cn.aikuiba.utils.AssertUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 司机的协议签署 服务实现类
 * </p>
 *
 * @author 蛮小满Sama
 * @since 2023-12-19
 */
@Service
public class DriverAgreementServiceImpl extends ServiceImpl<DriverAgreementMapper, DriverAgreement> implements IDriverAgreementService {

    @Override
    public PageResult<DriverAgreement> pageData(PageQueryWrapper<DriverAgreement> query) {
        //分页查询
        QueryWrapper<DriverAgreement> wrapper = new QueryWrapper<>();
        //实体类作为查询条件
        wrapper.setEntity(query.getQuery());
        //分页查询
        Page<DriverAgreement> page = super.page(new Page<>(query.getPage(), query.getRows()), wrapper);
        List<DriverAgreement> records = page.getRecords();


        return null;
    }

    /**
     * 1.获取前端传参并校验
     * 2.判断合同编号是否已存在
     * 3.保存合同信息
     *
     * @param dto
     */
    @Transactional
    @Override
    public void uploadSingedAgreement(UploadAgreementDTO dto) {
        Long driverId = dto.getDriverId();
        String agreementPathSigned = dto.getAgreementPathSigned();
        String agreementSn = dto.getAgreementSn();
        DriverAgreement driverAgreement = getOne(new LambdaQueryWrapper<DriverAgreement>().eq(DriverAgreement::getAgreementSn, agreementSn));
        AssertUtil.isNull(driverAgreement, ErrorCode.DRIVER_AGREEMENT_SN_EXIST);
        driverAgreement = new DriverAgreement();
        driverAgreement.setDriverId(driverId);
        driverAgreement.setAgreementSn(agreementSn);
        driverAgreement.setSendToDriverTime(new Date());
        driverAgreement.setDriverSendBackTime(new Date());
        driverAgreement.setAgreementPathOriginal(agreementPathSigned);
        driverAgreement.setAgreementPathSigned(agreementPathSigned);
        driverAgreement.setAgreementSignedStatus(Constants.Driver.AGREEMENT_SIGNED_STATUS_SIGNED);
        driverAgreement.setAgreementStatus(Constants.Driver.AGREEMENT_STATUS_SUCCESS);
        driverAgreement.setCreateTime(new Date());
        save(driverAgreement);
    }
}
