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
public class Employee implements Serializable {
    @Id
    @Getter
    private String uid;

    @Column(name = "name")
    @Getter
    private String name;

    @Column(name = "id_number")
    @Getter
    private Long idNumber;

    @Column(name = "sex")
    @Getter
    private String sex;

    @Column(name = "photo")
    @Getter
    private String photo;

    @Column(name = "tel")
    @Getter
    private String tel;

    @Column(name = "address")
    @Getter
    private String address;

    @Column(name = "nation")
    @Getter
    private String nation;

    @Column(name = "education")
    @Getter
    private String education;

    @Column(name = "job_state")
    @Getter
    private String jobState;

    @MapsId
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "uid")
    @Getter
    @JsonBackReference
    private Account account;

    @OneToMany(mappedBy = "employee")
    @Getter
    @JsonBackReference
    private List<Archive> archiveList;

    @OneToMany(mappedBy = "employee")
    @Getter
    @JsonBackReference
    private List<Authorization> authorizationList;
}
