package com.fwwb.hrms.po;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @Author: 周余民
 * @Date: Created in 16:22 2021/1/22
 */
@Entity
@Setter
@Getter
public class Company {
    @Id
    @Column(name = "uid")
    private String uid;

    @Column(name = "name")
    private String name;

    @Column(name = "tel")
    private String tel;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "company")
    @JsonBackReference
    private List<Archive> archiveList;

    @OneToOne(mappedBy = "company")
    @JsonBackReference
    private Hr hr;

}
