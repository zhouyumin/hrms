package com.fwwb.hrms.po;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @Author: 周余民
 * @Date: Created in 16:22 2021/1/22
 */
@Entity
@Setter
public class Account {
    @Id
    @Column(name = "uid")
    @Getter
    private String uid;

    @Column(name = "password")
    @Getter
    private String password;

    @Column(name = "identity")
    @Getter private String identity;

    @Column(name = "state")
    @Getter
    private String state;

    @Column(name = "file")
    @Getter
    private String file;

    @OneToOne(mappedBy = "account")
    @Getter
    private Employee employee;

    @OneToOne(mappedBy = "account")
    @Getter
    private Hr hr;
}
