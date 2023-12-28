package cn.aikuiba.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by 蛮小满Sama at 2023/12/27 16:17
 *
 * @description
 */
public interface IMinioService {
    String upload(MultipartFile file);

    void delete(String name);
}
