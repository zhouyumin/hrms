package com.fwwb.hrms.po;

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
public class Hr implements Serializable {
    @Id
    @Getter
    private String uid;

    @Column(name = "name")
    @Getter
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_uid")
    @Getter
    private Company company;

    @Column(name = "tel")
    @Getter
    private String tel;

    @Column(name = "sex")
    @Getter
    private String sex;

    @Column(name = "id_number")
    @Getter
    private Long idNumber;

    @MapsId
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "uid")
    @Getter
    private Account account;
}
