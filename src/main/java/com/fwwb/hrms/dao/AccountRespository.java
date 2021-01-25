package com.fwwb.hrms.dao;

import com.fwwb.hrms.po.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: 周余民
 * @Date: Created in 11:38 2021/1/25
 */
public interface AccountRespository extends JpaRepository<Account, String> {
   Account findByUidAndPassword(String uid, String password);
}
