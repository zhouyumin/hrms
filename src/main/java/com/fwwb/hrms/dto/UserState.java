package com.fwwb.hrms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

/**
 * @Author: 周余民
 * @Date: Created in 16:27 2021/3/29
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserState {
    private String identity;
    private String state;
}
