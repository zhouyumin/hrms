package com.fwwb.hrms.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: 周余民
 * @Date: Created in 17:13 2021/4/1
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizationDto {
    private String uid;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date StartDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endDate;
}
