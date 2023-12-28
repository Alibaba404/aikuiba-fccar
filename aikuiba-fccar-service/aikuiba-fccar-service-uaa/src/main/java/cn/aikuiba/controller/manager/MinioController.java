package cn.aikuiba.controller.manager;

import cn.aikuiba.result.JSONResult;
import cn.dev33.satoken.annotation.SaIgnore;
import com.alibaba.fastjson.JSON;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by 蛮小满Sama at 2023/12/27 15:53
 *
 * @description
 */
@Tag(name = "Mini接口", description = "文件上传相关接口")
@RequestMapping("/minio")
@RestController
public class MinioController {

    @SaIgnore // SaToken放行当前接口
    @Operation(summary = "文件上传", description = "文件上传")
    @Parameter(name = "file", description = "上传的文件", required = true)
    @PostMapping("/upload")
    public JSONResult<String> upload(MultipartFile file) {
        return JSONResult.success();
    }
}
