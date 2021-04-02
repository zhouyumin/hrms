package com.fwwb.hrms.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.Date;

/**
 * @Author: 周余民
 * @Date: Created in 21:26 2021/3/30
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "档案")
public class ArchiveDto {
    @ApiModelProperty(value = "员工姓名")
    String name;
    @ApiModelProperty(value = "身份证号")
    String idNumber;
    @ApiModelProperty(value = "电话")
    String tel;
    @ApiModelProperty(value = "职称")
    String title;
    @ApiModelProperty(value = "部门")
    String department;
    @ApiModelProperty(value = "任职日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    Date hireDate;
}
