package com.fwwb.hrms.service.impl;

import com.fwwb.hrms.dao.ArchiveRespository;
import com.fwwb.hrms.po.Archive;
import com.fwwb.hrms.service.ArchiveService;

import javax.annotation.Resource;

/**
 * @Author: 周余民
 * @Date: Created in 15:32 2021/1/29
 */
public class ArchiveImpl implements ArchiveService {
    @Resource
    ArchiveRespository archiveRespository;

    @Override
    public Archive getByArchive(String uid) {
        return archiveRespository.findById(uid).orElse(null);
    }
}
