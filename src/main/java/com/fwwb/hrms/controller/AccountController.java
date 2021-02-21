package com.fwwb.hrms.controller;

import com.fwwb.hrms.po.Account;
import com.fwwb.hrms.service.AccountService;
import com.fwwb.hrms.utils.Constant;
import com.fwwb.hrms.utils.Result;
import io.swagger.annotations.ApiOperation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
    @PostMapping(Constant.LOGIN)
    public Result login(@RequestParam("username") String username,
                        @RequestParam("password") String password){
      return accountService.checkAccount(username, password);
    }
    @ApiOperation(value = "注册", notes = "提交注册表单")
    @PostMapping(Constant.REGISTER)
    public Result register(@RequestParam("username")String username,
                           @RequestParam("password")String password,
                           @RequestParam("identity")String identity){
        if(identity.equals("HR")||identity.equals("Employee")){
            if(accountService.findAccountByUsername(username)){
                return Result.fail("此账号已存在");
            }else{
                accountService.registerAccount(username, password, identity);
                return Result.succ("注册成功");
            }
        }
        else {
            return Result.fail("错误");
        }

    }
	 
}
