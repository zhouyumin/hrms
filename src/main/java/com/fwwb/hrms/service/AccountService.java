package com.fwwb.hrms.service;

import com.fwwb.hrms.dto.UserState;
import com.fwwb.hrms.po.Account;

/**
 * @Author: 周余民
 * @Date: Created in 11:39 2021/1/25
 */
public interface AccountService {
    Account checkAccount(String uid, String password);
    Account getByUserName(String uid);
    boolean findAccountByUsername(String uid);
    void registerAccount(String uid, String password, String identify);
    UserState getIdentity(String uid);
}
