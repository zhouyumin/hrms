package com.fwwb.hrms.service;

import com.fwwb.hrms.po.Archive;
import com.fwwb.hrms.po.Hr;

/**
 * @Author: 周余民
 * @Date: Created in 15:32 2021/1/25
 */
public interface HrService {
    Hr getById(String uid);
    void save(Hr hr);
    Archive getByArchive(String uid);
}
