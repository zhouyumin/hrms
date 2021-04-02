package com.fwwb.hrms.dao;

import com.fwwb.hrms.dto.UserState;
import com.fwwb.hrms.po.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @Author: 周余民
 * @Date: Created in 11:38 2021/1/25
 */

public interface AccountRepository extends JpaRepository<Account, String> {
   Account findByUidAndPassword(String uid, String password);
   @Query("select new com.fwwb.hrms.dto.UserState(p.identity, p.state) from Account as p where p.uid = ?1")
   UserState getIdentity(String uid);
}
