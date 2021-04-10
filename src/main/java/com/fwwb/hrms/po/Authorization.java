package com.fwwb.hrms.po;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
* @Author: 周余民
* @Date: Created in 16:22 2021/1/22
*/
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Authorization{
    @Id
    @Column(name = "uid")
    private String uid;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "employee_uid")
    private Employee employee;
}
