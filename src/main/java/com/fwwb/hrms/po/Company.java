package com.fwwb.hrms.po;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @Author: 周余民
 * @Date: Created in 16:22 2021/1/22
 */
@Entity
@Data
public class Company {
    @Id
    private String uid;

    @Column(name = "name")
    private String name;

    @Column(name = "tel")
    private String tel;

    @Column(name = "address")
    private String address;

    @Column(name = "business_number")
    private String business_number;

    @OneToMany(mappedBy = "company")
    @JsonBackReference
    private List<Archive> archiveList;

    @MapsId
    @OneToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private Account account;

}
