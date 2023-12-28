package cn.aikuiba.service.impl;

import cn.aikuiba.constants.Constants;
import cn.aikuiba.constants.ErrorCode;
import cn.aikuiba.exception.GlobalException;
import cn.aikuiba.service.IMinioService;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by 蛮小满Sama at 2023/12/27 16:18
 *
 * @description
 */
@Service
public class MinioServiceImpl implements IMinioService {
    @Override
    public String upload(MultipartFile file) {
        String fileName;
        try {
            // 构建一个Minio客户端
            MinioClient minioClient = MinioClient.builder()
                    //创建容器时指定的账号
                    .credentials(Constants.Minio.KEY_ACCESS, Constants.Minio.KEY_SECRET)
                    //上传地址
                    .endpoint(Constants.Minio.ADDRESS).build();
            //处理文件名
            String oName = file.getOriginalFilename();
            fileName = System.currentTimeMillis() + oName.substring(oName.lastIndexOf("."));
            PutObjectArgs putObjectArgs = PutObjectArgs
                    .builder().object(fileName)    //文件名
                    .bucket(Constants.Minio.BUCKET)  //存储目录名
                    .stream(file.getInputStream(), //文件流，
                            file.getSize(),         //以及大小，
                            -1).build(); //-1代表不分片
            //执行上传
            minioClient.putObject(putObjectArgs);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(ErrorCode.API_MINIO_UPLOAD_ERROR);
        }
        return fileName;
    }

    @Override
    public void delete(String name) {
        try {
            // 构建一个Minio客户端
            MinioClient minioClient = MinioClient.builder()
                    //创建容器时指定的账号
                    .credentials(Constants.Minio.KEY_ACCESS, Constants.Minio.KEY_SECRET)
                    //上传地址
                    .endpoint(Constants.Minio.ADDRESS).build();
            minioClient.removeObject(Constants.Minio.BUCKET, name);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(ErrorCode.API_MINIO_DELETE_ERROR);
        }
    }
}
