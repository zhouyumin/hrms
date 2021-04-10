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
public class ArchiveDto {
    String name;
    String idNumber;
    String tel;
    String title;
    String department;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    Date hireDate;
}
