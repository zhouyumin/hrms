package com.fwwb.hrms.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: 周余民
 * @Date: Created in 16:22 2021/1/22
 */
@Entity
@Data
public class Archive {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name = "uid")
    private Integer uid;

    @Column(name = "bonus_penalty")
    private String bonusPenalty;

    @Column(name = "attendance")
    private String attendance;

    @Column(name = "comment")
    private String comment;

    @Column(name = "rate")
    private Integer rate;

    @Column(name = "team_ability")
    private Integer teamAbility;

    @Column(name = "performance")
    private Integer performance;

    @Column(name = "attitude")
    private Integer attitude;

    @Column(name = "title")
    private String title;

    @Column(name = "department")
    private String department;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "hire_date")
    private Date hireDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "departure_date")
    private Date departureDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_uid")
    private Company company;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_uid")
    private Employee employee;
}
