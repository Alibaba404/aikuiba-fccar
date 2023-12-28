package cn.aikuiba.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * Created by 蛮小满Sama at 2023/12/27 13:40
 *
 * @description
 */
@Data
public class DriverAgreementVO {
    @Schema(name = "driverId", description = "司机ID")
    private Long driverId;

    @Schema(name = "driverName", description = "司机姓名")
    private String driverName;

    @Schema(name = "agreementSn", description = "合同编号")
    private String agreementSn;

    @Schema(name = "sendToDriverTime", description = "平台寄出时间")
    private Date sendToDriverTime;

    @Schema(name = "driverSendBackTime", description = "司机寄回时间")
    private Date driverSendBackTime;

    @Schema(name = "agreementPathOriginal", description = "协议云储存地址:源文件")
    private String agreementPathOriginal;

    @Schema(name = "agreementPathSigned", description = "协议云储存地址:已签署")
    private String agreementPathSigned;

    @Schema(name = "agreementSignedStatus", description = "签署状态：0未生成，1已生成，2已寄出，3成功签署，4签署失败")
    private Integer agreementSignedStatus;

    @Schema(name = "agreementStatus", description = "合同状态:0:有效、1:作废")
    private Integer agreementStatus;

    @Schema(name = "createTime", description = "创建时间")
    private Date createTime;

    @Schema(name = "updateTime", description = "修改时间")
    private Date updateTime;
}
