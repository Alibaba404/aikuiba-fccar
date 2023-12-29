package cn.aikuiba.controller.oss;

import cn.aikuiba.pojo.param.UploadParam;
import cn.aikuiba.result.JSONResult;
import cn.aikuiba.template.AliOSSTemplate;
import cn.aikuiba.template.MinioOSSTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/oss")
public class OSSController {

    @Autowired
    private MinioOSSTemplate minioOSSTemplate;

    @Autowired
    private AliOSSTemplate aliOSSTemplate;

    /**
     * 文件上传阿里云oss
     *
     * @param file
     * @return
     */
    @PostMapping("/ali")
    public JSONResult<String> upload2Ali(@RequestPart("file") MultipartFile file, UploadParam uploadParam) {
        return JSONResult.success(aliOSSTemplate.upload(file, uploadParam));
    }

    /**
     * 文件上传到minio
     *
     * @param file
     * @param uploadParam
     * @return
     */
    @PostMapping("/minio")
    public JSONResult<String> upload2Minio(@RequestPart("file") MultipartFile file, UploadParam uploadParam) {
        return JSONResult.success(minioOSSTemplate.upload(file, uploadParam));
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
        minioOSSTemplate.delete(name);
        return JSONResult.success();
    }
}
