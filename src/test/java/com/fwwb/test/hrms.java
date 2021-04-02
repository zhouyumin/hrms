package com.fwwb.test;

import com.fwwb.hrms.HrmsApplication;
import com.fwwb.hrms.dao.AccountRepository;
import com.fwwb.hrms.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: 周余民
 * @Date: Created in 14:00 2021/1/25
 */
@SpringBootTest(classes = HrmsApplication.class)
public class hrms {
    @Autowired
    AccountRepository accountRespository;
    @Autowired
    AccountService accountService;
}
