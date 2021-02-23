package com.fwwb.hrms.po;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * @Author: 周余民
 * @Date: Created in 16:22 2021/1/22
 */
@Entity
@Setter
@Getter
public class Archive {
    @Id
    @Column(name = "uid")
    private long uid;

    @Column(name = "general_comment")
    private String generalComment;

    @Column(name = "bonus_penalty")
    private String bonusPenalty;

    @Column(name = "team_ability")
    private Integer teamAbility;

    @Column(name = "performance")
    private Integer performance;

    @Column(name = "attitude")
    private Integer attitude;

    @Column(name = "title")
    private String title;

    @Column(name = "hire_date")
    private Date hireDate;

    @Column(name = "departure_date")
    private Date departureDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_uid")
    @JsonBackReference
    private Company company;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_uid")
    private Employee employee;


    @OneToMany(mappedBy = "archive")
    private List<Authorization> authorizationList;
}
