package com.fwwb.hrms.controller;

import com.fwwb.hrms.dto.ArchiveDto;
import com.fwwb.hrms.dto.UpdateArchiveDto;
import com.fwwb.hrms.po.Archive;
import com.fwwb.hrms.po.Authorization;
import com.fwwb.hrms.po.Company;
import com.fwwb.hrms.po.Employee;
import com.fwwb.hrms.service.ArchiveService;
import com.fwwb.hrms.service.AuthorizationService;
import com.fwwb.hrms.service.CompanyService;
import com.fwwb.hrms.service.EmployeeService;
import com.fwwb.hrms.utils.Constant;
import com.fwwb.hrms.utils.JwtUtil;
import com.fwwb.hrms.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Author: 周余民
 * @Date: Created in 21:22 2021/3/30
 * @description:
 */
@RestController
public class ArchiveController {
    @Resource
    EmployeeService employeeService;
    @Resource
    ArchiveService archiveService;
    @Resource
    CompanyService companyService;
    @Resource
    AuthorizationService authorizationService;

    @ApiOperation(value = "添加档案", notes = "提交档案表单")
    @PostMapping(Constant.ADD_ARCHIVE)
    @RequiresRoles("Company")
    public Result addArchive(@RequestBody ArchiveDto archiveDto, HttpServletRequest request){
        Employee employee;
        try {
            employee = employeeService.getByIdNumber(archiveDto.getIdNumber());
        }catch (Exception e){
            return Result.fail("请输入正确的员工姓名和身份证号");
        }
        if(employee == null){
            return Result.fail("该员工未注册或认证");
        }
        if(!employee.getName().equals(archiveDto.getName())){
            return Result.fail("身份证号和姓名不匹配");
        }
        Archive archive = new Archive();
        BeanUtils.copyProperties(archiveDto, archive);
        archive.setEmployee(employee);
        String token = request.getHeader("Authorization");
        String username = JwtUtil.getUsername(token);
        Company company = companyService.getById(username);
        archive.setCompany(company);
        String uuid = UUID.randomUUID().toString().replace("-", "");
        archive.setUid(uuid);
        archiveService.saveArchive(archive);
        return Result.succ("添加成功",archive);
    }

    @ApiOperation(value = "更新档案", notes = "提交档案表单")
    @PostMapping(Constant.UPDATE_ARCHIVE)
    @RequiresRoles("Company")
    public Result updateArchive(@RequestBody UpdateArchiveDto updateArchiveDto, HttpServletRequest request){
        String token = request.getHeader("Authorization");
        String username = JwtUtil.getUsername(token);
        Company company = companyService.getById(username);
        Archive archive = archiveService.findByIdAndCompany(updateArchiveDto.getUid(), company);
        BeanUtils.copyProperties(updateArchiveDto, archive);
        archiveService.saveArchive(archive);
        return Result.succ("修改成功");
    }

    @ApiOperation(value = "获取档案", notes = "获取档案")
    @GetMapping(Constant.GET_ARCHIVE)
    @RequiresRoles("Employee")
    public Result getArchive(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        String username = JwtUtil.getUsername(token);
        Employee employee = employeeService.getById(username);
        List<Archive> res = archiveService.getByEmployee(employee);
        return Result.succ("获取成功", res);
    }

    @ApiOperation(value = "获取公司档案", notes = "获取公司员工档案")
    @GetMapping(Constant.GET_COMPANY_ARCHIVE)
    @RequiresRoles("Company")
    public Result getCompanyArchive(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        String username = JwtUtil.getUsername(token);
        Company company = companyService.getById(username);
        List<Archive> res = archiveService.getByCompany(company);
        return Result.succ("获取成功", res);
    }

    @ApiOperation(value = "调取档案", notes = "调取员工档案")
    @GetMapping(Constant.FETCH_ARCHIVE)
    @RequiresRoles("Company")
    public Result fetchArchive(@RequestParam("authorization")String authorization){
        Authorization res = authorizationService.fetchByUid(authorization);
        if(res == null){
            return Result.fail("授权码无效");
        }
        Date now = new Date();
        Date endDate = res.getEndDate();
        Date startDate = res.getStartDate();
        if(now.before(startDate)&&now.after(endDate)){
            return Result.fail("授权码不在有效期");
        }
        List<Archive> archives= archiveService.getByEmployee(res.getEmployee());
        List<Object> resDate = new ArrayList<>();
        resDate.add(res);
        resDate.add(archives);
        return Result.succ("获取成功", resDate);
    }
}
