package com.fwwb.hrms.service.impl;

import com.fwwb.hrms.dao.AccountRepository;
import com.fwwb.hrms.dto.UserState;
import com.fwwb.hrms.po.Account;
import com.fwwb.hrms.po.Company;
import com.fwwb.hrms.po.Employee;
import com.fwwb.hrms.service.AccountService;
import com.fwwb.hrms.service.EmployeeService;
import com.fwwb.hrms.service.CompanyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @Author: 周余民
 * @Date: Created in 11:40 2021/1/25
 */
@Service
public class AccountImpl implements AccountService {
    @Resource
    private AccountRepository accountRespository;
    @Resource
    private CompanyService companyService;
    @Resource
    private EmployeeService employeeService;

    @Override
    public Account checkAccount(String uid, String password) {
        return accountRespository.findByUidAndPassword(uid, password);
    }

    @Override
    public boolean findAccountByUsername(String uid) {
        return accountRespository.findById(uid).isPresent();
    }

    @Override
    public void registerAccount(String uid, String password, String identify) {
        Account account = new Account();
        account.setUid(uid);
        account.setPassword(password);
        account.setIdentity(identify);
        accountRespository.save(account);
        if (identify.equals("HR")) {
            Company company = new Company();
            company.setUid(account.getUid());
            company.setAccount(account);
            companyService.save(company);
        } else if (identify.equals("Employee")) {
            Employee employee = new Employee();
            employee.setUid(uid);
            employee.setAccount(account);
            employeeService.save(employee);
        }
    }

    @Override
    public UserState getIdentity(String uid) {
        return accountRespository.getIdentity(uid);
    }

    @Override
    public Account getByUserName(String uid) {
        Optional<Account> optional = accountRespository.findById(uid);
        return optional.orElse(null);
    }
}
