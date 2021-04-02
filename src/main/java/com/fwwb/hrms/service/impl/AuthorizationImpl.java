package com.fwwb.hrms.service.impl;

import com.fwwb.hrms.dao.AuthorizationRepository;
import com.fwwb.hrms.po.Authorization;
import com.fwwb.hrms.po.Employee;
import com.fwwb.hrms.service.AuthorizationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: 周余民
 * @Date: Created in 17:02 2021/4/1
 * @description:
 */
@Service
public class AuthorizationImpl implements AuthorizationService {
    @Resource
    AuthorizationRepository authorizationRepository;
    @Override
    public void save(Authorization authorization) {
        authorizationRepository.save(authorization);
    }

    @Override
    public List<Authorization> getAuthorization(Employee employee) {
        return authorizationRepository.findAllByEmployee(employee);
    }

    @Override
    public void deleteAuthorization(String uid) {
        authorizationRepository.deleteByUid(uid);
    }
}
