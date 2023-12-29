package cn.aikuiba.template;

import cn.aikuiba.constants.Constants;
import cn.aikuiba.constants.ErrorCode;
import cn.aikuiba.exception.GlobalException;
import cn.aikuiba.pojo.param.UploadParam;
import cn.aikuiba.utils.FileNameUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 蛮小满Sama at 2023/12/29 16:32
 *
 * @description
 */
public class AliOSSTemplate {
    //AccessKey ID:LTAI5tNKcqfuX4HeT9pCQDYQ
    //AccessKey Secret:CNgSe7lKl38iH7jaljLgJvpUT6nOvQ

    private OSS ossClient;

    public AliOSSTemplate() {
        init();
    }

    public void init() {
        // 获取RAM用户的访问密钥（AccessKey ID和AccessKey Secret）。
        String accessKeyId = Constants.Ali.OSS_ACCESS_KEY_ID;
        String accessKeySecret = Constants.Ali.OSS_ACCESS_KEY_SECRET;
        // 使用代码嵌入的RAM用户的访问密钥配置访问凭证。
        CredentialsProvider credentialsProvider = new DefaultCredentialProvider(accessKeyId, accessKeySecret);
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = Constants.Ali.OSS_ENDPOINT_URL;
        // 创建OSSClient实例。
        ossClient = new OSSClientBuilder().build(endpoint, credentialsProvider);
    }


    /**
     * 文件上传
     *
     * @param file        文件
     * @param uploadParam 上传的参数
     * @return
     */
    public String upload(MultipartFile file, UploadParam uploadParam) {
        // 完整的文件路径:https://aikuiba-fccar.oss-cn-chengdu.aliyuncs.com/test/%E6%96%87%E6%9C%AC.txt
        String objectName = FileNameUtil.randomFileName(file, uploadParam.getModel());
        try {
            ossClient.putObject(Constants.Ali.OSS_BUCKET_NAME, objectName, file.getInputStream());
        } catch (OSSException | IOException oe) {
            throw GlobalException.me(ErrorCode.API_ALI_OSS_UPLOAD_ERROR);
        } finally {
            if (ossClient != null) ossClient.shutdown();
        }
        // https://aikuiba-fccar.oss-cn-chengdu.aliyuncs.com/driver/2023/12/29/VZUOHAGAH9.webp
        return Constants.Ali.OSS_BASE_URL + objectName;
    }

    /**
     * 删除文件
     *
     * @param fileShortPath 文件短路径(不含http://oss-cn-chengdu.aliyuncs.com的路径)
     */
    public void remove(String fileShortPath) {
        try {
            // 删除文件。
            ossClient.deleteObject(Constants.Ali.OSS_BUCKET_NAME, fileShortPath);
        } catch (OSSException oe) {
            throw GlobalException.me(ErrorCode.API_ALI_OSS_UPLOAD_ERROR);
        } finally {
            if (ossClient != null) ossClient.shutdown();
        }
    }
}
