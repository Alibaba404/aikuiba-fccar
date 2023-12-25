package cn.aikuiba.controller.manager;

import cn.aikuiba.service.ICustomerWalletFlowService;
import cn.aikuiba.pojo.domain.CustomerWalletFlow;
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

@Tag(name = "钱包流水",description = "钱包流水")
@RestController
@RequestMapping("/manager/customerWalletFlow")
public class CustomerWalletFlowController{

    @Autowired
    public ICustomerWalletFlowService customerWalletFlowService;

    @Operation( summary= "保存CustomerWalletFlow",description = "基础对象保存接口")
    @Parameter(name = "customerWalletFlow",description = "保存的对象",required = true)
    @PostMapping
    public JSONResult save(@RequestBody @Valid CustomerWalletFlow customerWalletFlow){
        return JSONResult.success(customerWalletFlowService.save(customerWalletFlow));
    }

    @Operation( summary= "修改CustomerWalletFlow",description = "基础对象修改接口")
    @Parameter(name = "customerWalletFlow",description = "修改的对象",required = true)
    @PutMapping
    public JSONResult update(@RequestBody  @Valid CustomerWalletFlow customerWalletFlow){
        return JSONResult.success(customerWalletFlowService.updateById(customerWalletFlow));
    }

    //删除对象
    @Operation( summary= "删除CustomerWalletFlow",description = "基础对象删除接口，采用状态删除")
    @Parameter(name = "id",description = "删除的对象ID",required = true)
    @DeleteMapping(value="/{id}")
    public JSONResult delete(@PathVariable("id") Long id){
        return JSONResult.success(customerWalletFlowService.removeById(id));
    }

    //获取对象
    @Operation( summary= "获取CustomerWalletFlow",description = "基础对象获取接口")
    @Parameter(name = "id",description = "查询的对象ID",required = true)
    @GetMapping(value = "/{id}")
    public JSONResult get(@PathVariable("id")Long id){
        return JSONResult.success(customerWalletFlowService.getById(id));
    }

    //获取列表:PageQueryWrapper作为通用的查询对象
    @Operation( summary= "查询CustomerWalletFlow列表",description = "基础对象列表查询，不带分页")
    @Parameter(name = "query",description = "查询条件",required = true)
    @PostMapping(value = "/list")
    public JSONResult list(@RequestBody PageQueryWrapper<CustomerWalletFlow> query){
        QueryWrapper<CustomerWalletFlow> wrapper = new QueryWrapper<>();
        //实体类作为查询条件
        wrapper.setEntity(query.getQuery());
        return JSONResult.success(customerWalletFlowService.list(wrapper));
    }

    //分页查询
    @Operation( summary= "查询CustomerWalletFlow分页列表",description = "基础对象列表查询，带分页")
    @Parameter(name = "query",description = "查询条件，需要指定分页条件",required = true)
    @PostMapping(value = "/pagelist")
    public JSONResult page(@RequestBody PageQueryWrapper<CustomerWalletFlow> query){
        //分页查询
        Page<CustomerWalletFlow> page = new Page<CustomerWalletFlow>(query.getPage(),query.getRows());
        QueryWrapper<CustomerWalletFlow> wrapper = new QueryWrapper<>();
        //实体类作为查询条件
        wrapper.setEntity(query.getQuery());
        //分页查询
        page = customerWalletFlowService.page(page,wrapper);
        //返回结果
        return JSONResult.success(new PageResult<CustomerWalletFlow>(page.getTotal(),page.getRecords()));
    }

}
