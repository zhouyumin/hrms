package com.fwwb.hrms.controller;

import com.fwwb.hrms.dto.ArchiveDto;
import com.fwwb.hrms.po.Archive;
import com.fwwb.hrms.po.Company;
import com.fwwb.hrms.po.Employee;
import com.fwwb.hrms.service.ArchiveService;
import com.fwwb.hrms.service.CompanyService;
import com.fwwb.hrms.service.EmployeeService;
import com.fwwb.hrms.utils.Constant;
import com.fwwb.hrms.utils.JwtUtil;
import com.fwwb.hrms.utils.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
