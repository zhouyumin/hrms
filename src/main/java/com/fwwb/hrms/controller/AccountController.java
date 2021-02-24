package com.fwwb.hrms.controller;

import com.fwwb.hrms.po.Account;
import com.fwwb.hrms.service.AccountService;
import com.fwwb.hrms.service.EmployeeService;
import com.fwwb.hrms.service.HrService;
import com.fwwb.hrms.shiro.jwt.JwtToken;
import com.fwwb.hrms.shiro.jwt.JwtUtil;
import com.fwwb.hrms.utils.Constant;
import com.fwwb.hrms.utils.Result;
import io.swagger.annotations.ApiOperation;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
                        HttpServletRequest request,
                        HttpServletResponse response) {
        String token = request.getHeader("Authorization");
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new JwtToken(token));
        } catch (Exception e) {
            token = JwtUtil.createToken(username, password);
            subject.login(new JwtToken(token));
        }
        return Result.succ("登入成功", token);
    }

    @ApiOperation(value = "退出登录",notes = "退出登入")
    @PostMapping(Constant.LOGOUT)
    public Result logout(HttpServletRequest request, HttpServletResponse response){
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.logout();
        }catch (Exception e){
            return Result.fail("退出失败");
        }
        return Result.succ("成功退出");
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
