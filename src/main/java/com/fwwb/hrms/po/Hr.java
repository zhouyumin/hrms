package com.fwwb.hrms.po;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author: 周余民
 * @Date: Created in 16:22 2021/1/22
 */
@Entity
@Setter
@Getter
public class Hr implements Serializable {
    @Id
    private String uid;

    @Column(name = "name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_uid")
    private Company company;

    @Column(name = "tel")
    private String tel;

    @Column(name = "sex")
    private String sex;

    @Column(name = "id_number")
    private Long idNumber;

    @MapsId
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "uid")
    @JsonBackReference
    private Account account;
}
