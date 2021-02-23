package com.fwwb.hrms.po;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @Author: 周余民
 * @Date: Created in 16:22 2021/1/22
 */
@Entity
@Setter
@Getter
public class Employee implements Serializable {
    @Id
    private String uid;

    @Column(name = "name")
    private String name;

    @Column(name = "id_number")
    private Long idNumber;

    @Column(name = "sex")
    private String sex;

    @Column(name = "photo")
    private String photo;

    @Column(name = "tel")
    private String tel;

    @Column(name = "address")
    private String address;

    @Column(name = "nation")
    private String nation;

    @Column(name = "education")
    private String education;

    @Column(name = "job_state")
    private String jobState;

    @MapsId
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "uid")
    @JsonBackReference
    private Account account;

    @OneToMany(mappedBy = "employee")
    @JsonBackReference
    private List<Archive> archiveList;

    @OneToMany(mappedBy = "employee")
    @JsonBackReference
    private List<Authorization> authorizationList;
}
