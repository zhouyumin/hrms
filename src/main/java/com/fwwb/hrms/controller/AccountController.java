package com.fwwb.hrms.controller;

import com.fwwb.hrms.po.Account;
import com.fwwb.hrms.service.AccountService;
import com.fwwb.hrms.service.EmployeeService;
import com.fwwb.hrms.service.HrService;
import com.fwwb.hrms.utils.JwtUtil;
import com.fwwb.hrms.utils.Constant;
import com.fwwb.hrms.utils.Result;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 周余民
 * @Date: Created in 11:47 2021/1/25
 */
@RestController
public class AccountController {
    @Resource
    AccountService accountService;
    @Resource
    HrService hrService;
    @Resource
    EmployeeService employeeService;

    @ApiOperation(value = "登入", notes = "提交登入表单")
    @PostMapping(Constant.LOGIN)
    public Result login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpServletResponse response) {
        Account account = accountService.checkAccount(username, password);
        if (account != null) {
            Object res = null;
            String jwt = JwtUtil.creatToken(account.getUid(), account.getPassword());
            response.setHeader("Authorization", jwt);   //成功登陆返回一个Token令牌
            response.setHeader("Access-Control-Expose-Headers", "Authorization");
            if (account.getIdentity().equals("HR")) {
                res = hrService.getById(account.getUid());
            } else if (account.getIdentity().equals("Employee")) {
                res = employeeService.getById(account.getUid());
            }
            if (account.getState().equals("未审核")) {
                return Result.succ("账号未审核", res);
            } else {
                return Result.succ("账号已审核通过", res);
            }
        } else {
            return Result.fail("用户名或密码错误");
        }
    }

    @ApiOperation(value = "注册", notes = "提交注册表单")
    @PostMapping(Constant.REGISTER)
    public Result register(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("identity") String identity) {
        if (identity.equals("HR") || identity.equals("Employee")) {
            if (accountService.findAccountByUsername(username)) {
                return Result.fail("此账号已存在");
            } else {
                accountService.registerAccount(username, password, identity);
                return Result.succ("注册成功");
            }
        } else {
            return Result.fail("错误");
        }

    }

}
