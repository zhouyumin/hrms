package com.fwwb.hrms.controller;

import com.fwwb.hrms.po.Account;
import com.fwwb.hrms.service.AccountService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: 周余民
 * @Date: Created in 11:47 2021/1/25
 */
@RestController
public class AccountController {
    @Resource
    AccountService accountService;
    @ApiOperation(value = "登入", notes = "提交登入表单")
    @PostMapping("/login")
    public Object login(@RequestParam("username") String username,
                        @RequestParam("password") String password){
      return accountService.checkAccount(username, password);
    }
    @ApiOperation(value = "注册", notes = "提交注册表单")
    @PostMapping("/register")
    public String register(@RequestParam("username")String username,
                           @RequestParam("password")String password,
                           @RequestParam("identify")String identify){
        if(identify.equals("HR")||identify.equals("Employee")){
            if(accountService.findAccountByUsername(username)){
                return "此账号已存在";
            }else{
                accountService.registerAccount(username, password, identify);
                return "账号注册成功";
            }
        }
        else {
            return "error";
        }

    }
}
