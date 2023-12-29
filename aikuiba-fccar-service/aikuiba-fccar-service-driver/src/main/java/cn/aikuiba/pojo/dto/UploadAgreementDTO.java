package cn.aikuiba.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created by 蛮小满Sama at 2023/12/28 19:08
 *
 * @description
 */
@Data
public class UploadAgreementDTO {
    /*司机ID*/
    @NotNull(message = "司机ID不能为空")
    private Long driverId;
    /*已签署的合同地址*/
    @NotEmpty(message = "合同地址不能为空")
    private String agreementPathSigned;
    /*合同编号*/
    @NotEmpty(message = "合同编号不能为空")
    private String agreementSn;
}
