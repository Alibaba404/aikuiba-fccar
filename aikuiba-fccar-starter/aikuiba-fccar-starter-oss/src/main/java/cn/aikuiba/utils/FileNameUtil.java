package cn.aikuiba.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 蛮小满Sama at 2023/12/29 18:50
 *
 * @description
 */
public class FileNameUtil {

    public static String randomFileName(MultipartFile file, String model) {
        String filePath = "";
        if (StrUtil.isNotBlank(model)) {
            filePath = filePath + model;
        }
        // 拼接年月日路径
        filePath += new SimpleDateFormat("/yyyy/MM/dd/").format(new Date());
        // 随机文件名
        filePath += RandomUtil.randomStringUpper(10);
        // 完成路径
        return String.format("%s.%s", filePath, FileUtil.extName(file.getOriginalFilename()));
    }
}
