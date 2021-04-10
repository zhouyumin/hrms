package com.fwwb.hrms.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fwwb.hrms.po.Company;
import com.fwwb.hrms.po.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * @Author: 周余民
 * @Date: Created in 19:15 2021/4/6
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateArchiveDto {
    private String name;

    private String idNumber;

    private String bonusPenalty;

    private String attendance;

    private String comment;

    private Integer rate;

    private Integer teamAbility;

    private Integer performance;

    private Integer attitude;

    private String title;

    private String department;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date hireDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date departureDate;
}
