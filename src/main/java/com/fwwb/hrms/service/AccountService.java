package com.fwwb.hrms.service;

import com.fwwb.hrms.po.Account;
import com.fwwb.hrms.utils.Result;

/**
 * @Author: 周余民
 * @Date: Created in 11:39 2021/1/25
 */
public interface AccountService {
    Result checkAccount(String uid, String password);
    boolean findAccountByUsername(String uid);
    void registerAccount(String uid, String password, String identify);
}
