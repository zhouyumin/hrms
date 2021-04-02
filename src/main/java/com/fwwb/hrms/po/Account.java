package com.fwwb.hrms.po;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author: 周余民
 * @Date: Created in 16:22 2021/1/22
 */
@Entity
@Data
public class Account {
    @Id
    @Column(name = "uid")
    private String uid;

    @Column(name = "password")
    private String password;

    @Column(name = "identity")
    private String identity;

    @Column(name = "state")
    private String state = "未审核";

    @Column(name = "file")
    private String file;

    @OneToOne(mappedBy = "account")
    private Employee employee;

    @OneToOne(mappedBy = "account")
    private Company company;
}
