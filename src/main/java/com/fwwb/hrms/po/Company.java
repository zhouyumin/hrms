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
public class Company {
    @Id
    @Column(name = "uid")
    @Getter
    private String uid;

    @Column(name = "name")
    @Getter
    private String name;

    @Column(name = "tel")
    @Getter
    private String tel;

    @Column(name = "address")
    @Getter
    private String address;

    @OneToMany(mappedBy = "company")
    @Getter
    @JsonBackReference
    private List<Archive> archiveList;

    @OneToOne(mappedBy = "company")
    @Getter
    @JsonBackReference
    private Hr hr;

}
