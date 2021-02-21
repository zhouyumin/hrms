package com.fwwb.hrms.service.impl;

import com.fwwb.hrms.dao.ArchiveRespository;
import com.fwwb.hrms.dao.HrRespository;
import com.fwwb.hrms.po.Archive;
import com.fwwb.hrms.po.Hr;
import com.fwwb.hrms.service.HrService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: 周余民
 * @Date: Created in 15:33 2021/1/25
 */
@Service
public class HrImpl implements HrService {
    @Resource
    HrRespository hrRespository;
    @Override
    public Hr getById(String uid) {
        return hrRespository.findById(uid).orElse(null);
    }

    @Override
    public void save(Hr hr) {
        hrRespository.save(hr);
    }
}
