package cn.aikuiba.controller.manager;

import cn.aikuiba.service.IPermissionService;
import cn.aikuiba.pojo.domain.Permission;
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

@Tag(name = "权限表",description = "权限表")
@RestController
@RequestMapping("/manager/permission")
public class PermissionController{

    @Autowired
    public IPermissionService permissionService;

    @Operation( summary= "保存Permission",description = "基础对象保存接口")
    @Parameter(name = "permission",description = "保存的对象",required = true)
    @PostMapping
    public JSONResult save(@RequestBody @Valid Permission permission){
        return JSONResult.success(permissionService.save(permission));
    }

    @Operation( summary= "修改Permission",description = "基础对象修改接口")
    @Parameter(name = "permission",description = "修改的对象",required = true)
    @PutMapping
    public JSONResult update(@RequestBody  @Valid Permission permission){
        return JSONResult.success(permissionService.updateById(permission));
    }

    //删除对象
    @Operation( summary= "删除Permission",description = "基础对象删除接口，采用状态删除")
    @Parameter(name = "id",description = "删除的对象ID",required = true)
    @DeleteMapping(value="/{id}")
    public JSONResult delete(@PathVariable("id") Long id){
        return JSONResult.success(permissionService.removeById(id));
    }

    //获取对象
    @Operation( summary= "获取Permission",description = "基础对象获取接口")
    @Parameter(name = "id",description = "查询的对象ID",required = true)
    @GetMapping(value = "/{id}")
    public JSONResult get(@PathVariable("id")Long id){
        return JSONResult.success(permissionService.getById(id));
    }

    //获取列表:PageQueryWrapper作为通用的查询对象
    @Operation( summary= "查询Permission列表",description = "基础对象列表查询，不带分页")
    @Parameter(name = "query",description = "查询条件",required = true)
    @PostMapping(value = "/list")
    public JSONResult list(@RequestBody PageQueryWrapper<Permission> query){
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        //实体类作为查询条件
        wrapper.setEntity(query.getQuery());
        return JSONResult.success(permissionService.list(wrapper));
    }

    //分页查询
    @Operation( summary= "查询Permission分页列表",description = "基础对象列表查询，带分页")
    @Parameter(name = "query",description = "查询条件，需要指定分页条件",required = true)
    @PostMapping(value = "/pagelist")
    public JSONResult page(@RequestBody PageQueryWrapper<Permission> query){
        //分页查询
        Page<Permission> page = new Page<Permission>(query.getPage(),query.getRows());
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        //实体类作为查询条件
        wrapper.setEntity(query.getQuery());
        //分页查询
        page = permissionService.page(page,wrapper);
        //返回结果
        return JSONResult.success(new PageResult<Permission>(page.getTotal(),page.getRecords()));
    }

}
