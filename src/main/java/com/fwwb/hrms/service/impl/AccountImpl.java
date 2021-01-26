package com.fwwb.hrms.service.impl;

import com.fwwb.hrms.dao.AccountRespository;
import com.fwwb.hrms.po.Account;
import com.fwwb.hrms.po.Employee;
import com.fwwb.hrms.po.Hr;
import com.fwwb.hrms.service.AccountService;
import com.fwwb.hrms.service.EmployeeService;
import com.fwwb.hrms.service.HrService;
import com.fwwb.hrms.utils.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: 周余民
 * @Date: Created in 11:40 2021/1/25
 */
@Service
public class AccountImpl implements AccountService {
    @Resource
    private AccountRespository accountRespository;
    @Resource
    private HrService hrService;
    @Resource
    private EmployeeService employeeService;
    @Override
    public Result checkAccount(String uid, String password) {
        Account account = accountRespository.findByUidAndPassword(uid, password);
        if(account!=null){
            Object res = null;
            if(account.getIdentity().equals("HR")){
                res = hrService.getById(account.getUid());
            }else if(account.getIdentity().equals("Employee")){
                res = employeeService.getById(account.getUid());
            }
            if(account.getState().equals("未审核")){
                return Result.succ("账号未审核", res);
            } else {
                return Result.succ("账号已审核通过",res);
            }
        }else {
            return Result.fail("用户名或密码错误");
        }
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
        if(identify.equals("HR")){
            Hr hr = new Hr();
            hr.setUid(account.getUid());
            hr.setAccount(account);
            hrService.save(hr);
        }else if(identify.equals("Employee")){
            Employee employee = new Employee();
            employee.setUid(account.getUid());
            employee.setAccount(account);
            employeeService.save(employee);
        }
    }
}
