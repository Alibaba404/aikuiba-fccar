package cn.aikuiba.service.impl;

import cn.aikuiba.pojo.domain.DriverAgreement;
import cn.aikuiba.mapper.DriverAgreementMapper;
import cn.aikuiba.pojo.query.PageQueryWrapper;
import cn.aikuiba.result.PageResult;
import cn.aikuiba.service.IDriverAgreementService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
}
