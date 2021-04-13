package com.fwwb.hrms.controller;

import com.fwwb.hrms.dto.CompanyDto;
import com.fwwb.hrms.po.Account;
import com.fwwb.hrms.po.Company;
import com.fwwb.hrms.service.AccountService;
import com.fwwb.hrms.service.CompanyService;
import com.fwwb.hrms.utils.Constant;
import com.fwwb.hrms.utils.JwtUtil;
import com.fwwb.hrms.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 周余民
 * @Date: Created in 22:47 2021/3/29
 * @description:
 */
@RestController
public class CompanyController {
    @Resource
    CompanyService companyService;
    @Resource
    AccountService accountService;
    @RequiresRoles("Company")
    @ApiOperation(value = "认证", notes = "公司提交认证")
    @PostMapping(Constant.AUTHOR_COMPANY)
    public Result author_company(@RequestBody CompanyDto companyDto, HttpServletRequest request){
        String token = request.getHeader("Authorization");
        String username = JwtUtil.getUsername(token);
        Company company = new Company();
        BeanUtils.copyProperties(companyDto, company);
        System.out.println(companyDto);
        System.out.println(company);
        Account account = accountService.getByUserName(username);
        account.setState("已审核");
        company.setUid(username);
        company.setAccount(account);
        companyService.save(company);
        return Result.succ("认证成功");
    }
}
