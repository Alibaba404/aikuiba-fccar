package cn.aikuiba.template;

import cn.aikuiba.constants.Constants;
import cn.aikuiba.constants.ErrorCode;
import cn.aikuiba.exception.GlobalException;
import cn.aikuiba.pojo.param.UploadParam;
import cn.aikuiba.utils.FileNameUtil;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by 蛮小满Sama at 2023/12/23 11:11
 *
 * @description Minio对象存储
 */
@Slf4j
public class MinioOSSTemplate {

    public String upload(MultipartFile file, UploadParam uploadParam) {
        String fileName;
        try {
            // 构建一个Minio客户端
            MinioClient minioClient = MinioClient.builder()
                    //创建容器时指定的账号
                    .credentials(Constants.Minio.KEY_ACCESS, Constants.Minio.KEY_SECRET)
                    //上传地址
                    .endpoint(Constants.Minio.ADDRESS).build();
            fileName = FileNameUtil.randomFileName(file, uploadParam.getModel());
            PutObjectArgs putObjectArgs = PutObjectArgs
                    .builder().object(fileName)    //文件名
                    .bucket(Constants.Minio.BUCKET_NAME)  //存储目录名
                    .stream(file.getInputStream(), //文件流，
                            file.getSize(),         //以及大小，
                            -1).build(); //-1代表不分片
            //执行上传
            minioClient.putObject(putObjectArgs);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(ErrorCode.API_MINIO_UPLOAD_ERROR);
        }
        return Constants.Minio.ADDRESS + Constants.Minio.BUCKET_NAME + "/" + fileName;
    }

    public void delete(String name) {
        try {
            // 构建一个Minio客户端
            MinioClient minioClient = MinioClient.builder()
                    //创建容器时指定的账号
                    .credentials(Constants.Minio.KEY_ACCESS, Constants.Minio.KEY_SECRET)
                    //上传地址
                    .endpoint(Constants.Minio.ADDRESS).build();
            minioClient.removeObject(Constants.Minio.BUCKET_NAME, name);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(ErrorCode.API_MINIO_DELETE_ERROR);
        }
    }
}
