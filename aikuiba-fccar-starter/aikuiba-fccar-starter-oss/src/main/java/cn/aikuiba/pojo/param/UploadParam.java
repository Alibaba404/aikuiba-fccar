package cn.aikuiba.pojo.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Created by 蛮小满Sama at 2023/12/29 17:17
 *
 * @description
 */
@Data
public class UploadParam {

    /*文件存储的模块名作为目录*/
    private String model;
}
