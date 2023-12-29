package cn.aikuiba.controller.manager;

import cn.aikuiba.pojo.domain.DriverAgreement;
import cn.aikuiba.pojo.dto.UploadAgreementDTO;
import cn.aikuiba.pojo.query.PageQueryWrapper;
import cn.aikuiba.result.JSONResult;
import cn.aikuiba.result.PageResult;
import cn.aikuiba.service.IDriverAgreementService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "司机的协议签署", description = "司机的协议签署")
@RestController
@RequestMapping("/manager/driverAgreement")
public class DriverAgreementController {

    @Autowired
    public IDriverAgreementService driverAgreementService;

    @Operation(summary = "保存DriverAgreement", description = "基础对象保存接口")
    @Parameter(name = "driverAgreement", description = "保存的对象", required = true)
    @PostMapping
    public JSONResult save(@RequestBody @Valid DriverAgreement driverAgreement) {
        return JSONResult.success(driverAgreementService.save(driverAgreement));
    }

    @Operation(summary = "修改DriverAgreement", description = "基础对象修改接口")
    @Parameter(name = "driverAgreement", description = "修改的对象", required = true)
    @PutMapping
    public JSONResult update(@RequestBody @Valid DriverAgreement driverAgreement) {
        return JSONResult.success(driverAgreementService.updateById(driverAgreement));
    }

    //删除对象
    @Operation(summary = "删除DriverAgreement", description = "基础对象删除接口，采用状态删除")
    @Parameter(name = "id", description = "删除的对象ID", required = true)
    @DeleteMapping(value = "/{id}")
    public JSONResult delete(@PathVariable("id") Long id) {
        return JSONResult.success(driverAgreementService.removeById(id));
    }

    //获取对象
    @Operation(summary = "获取DriverAgreement", description = "基础对象获取接口")
    @Parameter(name = "id", description = "查询的对象ID", required = true)
    @GetMapping(value = "/{id}")
    public JSONResult get(@PathVariable("id") Long id) {
        return JSONResult.success(driverAgreementService.getById(id));
    }

    //获取列表:PageQueryWrapper作为通用的查询对象
    @Operation(summary = "查询DriverAgreement列表", description = "基础对象列表查询，不带分页")
    @Parameter(name = "query", description = "查询条件", required = true)
    @PostMapping(value = "/list")
    public JSONResult list(@RequestBody PageQueryWrapper<DriverAgreement> query) {
        QueryWrapper<DriverAgreement> wrapper = new QueryWrapper<>();
        //实体类作为查询条件
        wrapper.setEntity(query.getQuery());
        return JSONResult.success(driverAgreementService.list(wrapper));
    }

    //分页查询
    @Operation(summary = "查询DriverAgreement分页列表", description = "基础对象列表查询，带分页")
    @Parameter(name = "query", description = "查询条件，需要指定分页条件", required = true)
    @PostMapping(value = "/pagelist")
    public JSONResult page(@RequestBody PageQueryWrapper<DriverAgreement> query) {
        //分页查询
        Page<DriverAgreement> page = new Page<DriverAgreement>(query.getPage(), query.getRows());
        QueryWrapper<DriverAgreement> wrapper = new QueryWrapper<>();
        //实体类作为查询条件
        wrapper.setEntity(query.getQuery());
        //分页查询
        page = driverAgreementService.page(page, wrapper);
        //返回结果
        return JSONResult.success(new PageResult<DriverAgreement>(page.getTotal(), page.getRecords()));
    }


    //分页查询
    @Operation(summary = "查询DriverAgreement分页列表", description = "基础对象列表查询，带分页")
    @Parameter(name = "query", description = "查询条件，需要指定分页条件", required = true)
    @PostMapping(value = "/pageData")
    public JSONResult<PageResult<DriverAgreement>> pageData(@RequestBody PageQueryWrapper<DriverAgreement> query) {
        //返回结果
        return JSONResult.success(driverAgreementService.pageData(query));
    }

    @Operation(summary = "司机合同上传", description = "司机合同上传")
    @Parameter(name = "dto", description = "合同上传对象", required = true)
    @PostMapping("/uploadSingedAgreement")
    public JSONResult<Valid> uploadSingedAgreement(@RequestBody @Valid UploadAgreementDTO dto) {
        driverAgreementService.uploadSingedAgreement(dto);
        return JSONResult.success();
    }

}
