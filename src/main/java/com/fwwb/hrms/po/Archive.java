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
public class Archive {
    @Id
    @Column(name = "uid")
    @Getter
    private long uid;

    @Column(name = "general_comment")
    @Getter
    private String generalComment;

    @Column(name = "bonus_penalty")
    @Getter
    private String bonusPenalty;

    @Column(name = "team_ability")
    @Getter
    private Integer teamAbility;

    @Column(name = "performance")
    @Getter
    private Integer performance;

    @Column(name = "attitude")
    @Getter
    private Integer attitude;

    @Column(name = "title")
    @Getter
    private String title;

    @Column(name = "hire_date")
    @Getter
    private Date hireDate;

    @Column(name = "departure_date")
    @Getter
    private Date departureDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_uid")
    @Getter
    @JsonBackReference
    private Company company;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_uid")
    @Getter
    private Employee employee;


    @OneToMany(mappedBy = "archive")
    @Getter
    private List<Authorization> authorizationList;
}
