package cn.aikuiba.controller.minio;

import cn.aikuiba.service.IMinioService;
import cn.aikuiba.result.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * MinIO文件服务器
 * Created by 蛮小满Sama at 2023/12/27 14:46
 *
 * @description MinIO相关
 */
@RestController
@RequestMapping("/minio")
public class MinioController {

    @Autowired
    private IMinioService minioService;

    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    @PostMapping
    public JSONResult<String> upload(@RequestPart("file") MultipartFile file) {
        return JSONResult.success(minioService.upload(file));
    }

    /**
     * 删除Minio上的文件
     *
     * @param name 文件名称
     * @return
     * @throws Exception
     */
    @DeleteMapping("/{name}")
    public JSONResult<String> delete(@PathVariable("name") String name) {
        minioService.delete(name);
        return JSONResult.success();
    }
}
