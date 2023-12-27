package cn.aikuiba.controller.manager;

import cn.aikuiba.pojo.manager.dto.LoginDTO;
import cn.aikuiba.pojo.vo.LoginInfoVO;
import cn.aikuiba.service.ILoginService;
import cn.aikuiba.pojo.domain.Login;
import cn.dev33.satoken.annotation.SaIgnore;
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

@Tag(name = "登录表", description = "登录表")
@RestController
@RequestMapping("/manager/login")
public class LoginController {

    @Autowired
    public ILoginService loginService;

    @SaIgnore // SaToken放行当前接口
    @Operation(summary = "管理端登录", description = "管理端登录")
    @Parameter(name = "dto", description = "登录对象", required = true)
    @PostMapping("/admin")
    public JSONResult<LoginInfoVO> login(@RequestBody @Valid LoginDTO dto) {
        return JSONResult.success(loginService.login(dto));
    }

    @Operation(summary = "保存Login", description = "基础对象保存接口")
    @Parameter(name = "login", description = "保存的对象", required = true)
    @PostMapping
    public JSONResult save(@RequestBody @Valid Login login) {
        return JSONResult.success(loginService.save(login));
    }

    @Operation(summary = "修改Login", description = "基础对象修改接口")
    @Parameter(name = "login", description = "修改的对象", required = true)
    @PutMapping
    public JSONResult update(@RequestBody @Valid Login login) {
        return JSONResult.success(loginService.updateById(login));
    }

    //删除对象
    @Operation(summary = "删除Login", description = "基础对象删除接口，采用状态删除")
    @Parameter(name = "id", description = "删除的对象ID", required = true)
    @DeleteMapping(value = "/{id}")
    public JSONResult delete(@PathVariable("id") Long id) {
        return JSONResult.success(loginService.removeById(id));
    }

    //获取对象
    @Operation(summary = "获取Login", description = "基础对象获取接口")
    @Parameter(name = "id", description = "查询的对象ID", required = true)
    @GetMapping(value = "/{id}")
    public JSONResult get(@PathVariable("id") Long id) {
        return JSONResult.success(loginService.getById(id));
    }

    //获取列表:PageQueryWrapper作为通用的查询对象
    @Operation(summary = "查询Login列表", description = "基础对象列表查询，不带分页")
    @Parameter(name = "query", description = "查询条件", required = true)
    @PostMapping(value = "/list")
    public JSONResult list(@RequestBody PageQueryWrapper<Login> query) {
        QueryWrapper<Login> wrapper = new QueryWrapper<>();
        //实体类作为查询条件
        wrapper.setEntity(query.getQuery());
        return JSONResult.success(loginService.list(wrapper));
    }

    //分页查询
    @Operation(summary = "查询Login分页列表", description = "基础对象列表查询，带分页")
    @Parameter(name = "query", description = "查询条件，需要指定分页条件", required = true)
    @PostMapping(value = "/pagelist")
    public JSONResult page(@RequestBody PageQueryWrapper<Login> query) {
        //分页查询
        Page<Login> page = new Page<Login>(query.getPage(), query.getRows());
        QueryWrapper<Login> wrapper = new QueryWrapper<>();
        //实体类作为查询条件
        wrapper.setEntity(query.getQuery());
        //分页查询
        page = loginService.page(page, wrapper);
        //返回结果
        return JSONResult.success(new PageResult<Login>(page.getTotal(), page.getRecords()));
    }

}
