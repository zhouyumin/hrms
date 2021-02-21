package com.fwwb.hrms.service;

import com.fwwb.hrms.po.Archive;

/**
 * @Author: 周余民
 * @Date: Created in 15:31 2021/1/29
 */
public interface ArchiveService {
    Archive getByArchive(String uid);
}
