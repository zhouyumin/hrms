package com.fwwb.hrms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 周余民
 * @Date: Created in 22:44 2021/3/29
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private String name;
    private String sex;
    private String id_number;
    private String tel;
    private String education;
}
