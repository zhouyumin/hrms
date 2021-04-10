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
import java.util.List;

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
        Employee employee = employeeService.getByIdNumber(archiveDto.getIdNumber());
        Archive archive = new Archive();
        BeanUtils.copyProperties(archiveDto, archive);
        archive.setEmployee(employee);
        String token = request.getHeader("Authorization");
        String username = JwtUtil.getUsername(token);
        Company company = companyService.getById(username);
        archive.setCompany(company);
        archiveService.saveArchive(archive);
        return Result.succ("添加成功");
    }

    @ApiOperation(value = "更新档案", notes = "提交档案表单")
    @PostMapping(Constant.UPDATE_ARCHIVE)
    @RequiresRoles("Company")
    public Result updateArchive(@RequestBody UpdateArchiveDto updateArchiveDto, HttpServletRequest request){
        Employee employee = employeeService.getByIdNumber(updateArchiveDto.getIdNumber());
        String token = request.getHeader("Authorization");
        String username = JwtUtil.getUsername(token);
        Company company = companyService.getById(username);
        Archive archive = archiveService.findByEmployeeAndCompany(employee, company);
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
        return Result.succ("获取成功", res);
    }
}
