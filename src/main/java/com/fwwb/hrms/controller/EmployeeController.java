package com.fwwb.hrms.controller;

import com.fwwb.hrms.dto.EmployeeDto;
import com.fwwb.hrms.po.Account;
import com.fwwb.hrms.po.Employee;
import com.fwwb.hrms.service.AccountService;
import com.fwwb.hrms.service.EmployeeService;
import com.fwwb.hrms.utils.Constant;
import com.fwwb.hrms.utils.JwtUtil;
import com.fwwb.hrms.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 周余民
 * @Date: Created in 22:46 2021/3/29
 * @description:
 */
@RestController
public class EmployeeController {
    @Resource
    EmployeeService employeeService;
    @Resource
    AccountService accountService;
    @RequiresRoles("Employee")
    @PostMapping(Constant.AUTHOR_EMPLOYEE)
    @ApiOperation(value = "认证", notes = "个人提交认证")
    public Result author_employee(@RequestBody EmployeeDto employeeDto, HttpServletRequest request){
        String token = request.getHeader("Authorization");
        String username = JwtUtil.getUsername(token);
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDto, employee);
        Account account = accountService.getByUserName(username);
        account.setState("已审核");
        employee.setUid(username);
        employee.setAccount(account);
        employeeService.save(employee);
        return Result.succ("认证成功");
    }
}
