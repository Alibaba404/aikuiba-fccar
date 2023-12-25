package cn.aikuiba.controller.manager;

import cn.aikuiba.service.ICustomerFineService;
import cn.aikuiba.pojo.domain.CustomerFine;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.aikuiba.pojo.query.PageQueryWrapper;
import cn.aikuiba.result.JSONResult;
import cn.aikuiba.result.PageResult;

@Tag(name = "乘客罚款",description = "乘客罚款")
@RestController
@RequestMapping("/manager/customerFine")
public class CustomerFineController{

    @Autowired
    public ICustomerFineService customerFineService;

    @Operation( summary= "保存CustomerFine",description = "基础对象保存接口")
    @Parameter(name = "customerFine",description = "保存的对象",required = true)
    @PostMapping
    public JSONResult save(@RequestBody @Valid CustomerFine customerFine){
        return JSONResult.success(customerFineService.save(customerFine));
    }

    @Operation( summary= "修改CustomerFine",description = "基础对象修改接口")
    @Parameter(name = "customerFine",description = "修改的对象",required = true)
    @PutMapping
    public JSONResult update(@RequestBody  @Valid CustomerFine customerFine){
        return JSONResult.success(customerFineService.updateById(customerFine));
    }

    //删除对象
    @Operation( summary= "删除CustomerFine",description = "基础对象删除接口，采用状态删除")
    @Parameter(name = "id",description = "删除的对象ID",required = true)
    @DeleteMapping(value="/{id}")
    public JSONResult delete(@PathVariable("id") Long id){
        return JSONResult.success(customerFineService.removeById(id));
    }

    //获取对象
    @Operation( summary= "获取CustomerFine",description = "基础对象获取接口")
    @Parameter(name = "id",description = "查询的对象ID",required = true)
    @GetMapping(value = "/{id}")
    public JSONResult get(@PathVariable("id")Long id){
        return JSONResult.success(customerFineService.getById(id));
    }

    //获取列表:PageQueryWrapper作为通用的查询对象
    @Operation( summary= "查询CustomerFine列表",description = "基础对象列表查询，不带分页")
    @Parameter(name = "query",description = "查询条件",required = true)
    @PostMapping(value = "/list")
    public JSONResult list(@RequestBody PageQueryWrapper<CustomerFine> query){
        QueryWrapper<CustomerFine> wrapper = new QueryWrapper<>();
        //实体类作为查询条件
        wrapper.setEntity(query.getQuery());
        return JSONResult.success(customerFineService.list(wrapper));
    }

    //分页查询
    @Operation( summary= "查询CustomerFine分页列表",description = "基础对象列表查询，带分页")
    @Parameter(name = "query",description = "查询条件，需要指定分页条件",required = true)
    @PostMapping(value = "/pagelist")
    public JSONResult page(@RequestBody PageQueryWrapper<CustomerFine> query){
        //分页查询
        Page<CustomerFine> page = new Page<CustomerFine>(query.getPage(),query.getRows());
        QueryWrapper<CustomerFine> wrapper = new QueryWrapper<>();
        //实体类作为查询条件
        wrapper.setEntity(query.getQuery());
        //分页查询
        page = customerFineService.page(page,wrapper);
        //返回结果
        return JSONResult.success(new PageResult<CustomerFine>(page.getTotal(),page.getRecords()));
    }

}
