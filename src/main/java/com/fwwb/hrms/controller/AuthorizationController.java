package com.fwwb.hrms.controller;

import com.fwwb.hrms.dto.AuthorizationDto;
import com.fwwb.hrms.po.Authorization;
import com.fwwb.hrms.po.Employee;
import com.fwwb.hrms.service.AuthorizationService;
import com.fwwb.hrms.service.EmployeeService;
import com.fwwb.hrms.utils.Constant;
import com.fwwb.hrms.utils.JwtUtil;
import com.fwwb.hrms.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

/**
 * @Author: 周余民
 * @Date: Created in 17:03 2021/4/1
 * @description:
 */
@RestController
public class AuthorizationController {
    @Resource
    EmployeeService employeeService;
    @Resource
    AuthorizationService authorizationService;

    @ApiOperation(value = "添加授权码", notes = "添加授权码")
    @RequiresRoles("Employee")
    @PostMapping(Constant.ADD_AUTHORIZATION)
    public Result addAuthorization(@RequestBody AuthorizationDto authorizationDto, HttpServletRequest request){
        String uuid = UUID.randomUUID().toString().replace("-", "");
        Authorization authorization = new Authorization();
        BeanUtils.copyProperties(authorizationDto, authorization);
        authorization.setUid(uuid);
        String token = request.getHeader("Authorization");
        String username = JwtUtil.getUsername(token);
        System.out.println(authorization);
        Employee employee = employeeService.getById(username);
        authorization.setEmployee(employee);
        authorizationService.save(authorization);
        return Result.succ("添加成功", uuid);
    }

    @ApiOperation(value = "获取授权码", notes = "获取授权码")
    @RequiresRoles("Employee")
    @GetMapping(Constant.GET_AUTHORIZATION)
    public Result getAuthorization(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        String username = JwtUtil.getUsername(token);
        Employee employee = employeeService.getById(username);
        List<AuthorizationDto> res = authorizationService.getAuthorization(employee);
        return Result.succ("获取成功", res);
    }

    @ApiOperation(value = "更新授权码", notes = "更新授权码")
    @RequiresRoles("Employee")
    @PostMapping(Constant.UPDATE_AUTHORIZATION)
    public Result updateAuthorization(@RequestBody AuthorizationDto authorizationDto, HttpServletRequest request){
        Authorization authorization = new Authorization();
        BeanUtils.copyProperties(authorizationDto, authorization);
        String token = request.getHeader("Authorization");
        String username = JwtUtil.getUsername(token);
        Employee employee = employeeService.getById(username);
        System.out.println(authorization);
        authorization.setEmployee(employee);
        authorizationService.save(authorization);
        return Result.succ("更新成功");
    }

    @ApiOperation(value = "删除授权码", notes = "删除授权码")
    @RequiresRoles("Employee")
    @DeleteMapping(Constant.DEL_AUTHORIZATION)
    public Result delAuthorization(@RequestParam("uid")String uid){
        authorizationService.deleteAuthorization(uid);
       return Result.succ("删除成功");
    }
}
