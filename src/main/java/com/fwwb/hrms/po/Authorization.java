package com.fwwb.hrms.po;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

/**
* @Author: 周余民
* @Date: Created in 16:22 2021/1/22
*/
@Entity
@Setter
@Getter
public class Authorization {
    @Id
    @Column(name = "uid")
    private int uid;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "archive_uid")
    private Archive archive;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_uid")
    private Employee employee;
}
