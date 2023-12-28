package cn.aikuiba.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * Created by 蛮小满Sama at 2023/12/27 17:18
 *
 * @description
 */
@Data
public class DriverVO {

    @Schema(name = "id", description = "同loginId")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;


    @Schema(name = "nickName", description = "司机姓名")
    private String nickName;
}
