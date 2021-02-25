package com.fwwb.hrms.shiro;

import com.fwwb.hrms.shiro.jwt.JwtToken;
import com.fwwb.hrms.utils.JwtUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Service;

import com.fwwb.hrms.po.Account;
import com.fwwb.hrms.service.AccountService;

import javax.annotation.Resource;

/**
 *  @Author: 黄天赐
 *  @Date: Created in 10:41 2021/2/21
 */

@Service
public class AccountRealm extends AuthorizingRealm {

    @Resource
    private AccountService accountService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        String uid = JwtUtil.getUsername(token);
        //通过username从数据库中查找 User对象
        Account account = accountService.getByUserName(uid);
        if (account == null || !JwtUtil.verify(token, uid, account.getPassword())) {
            throw new UnauthenticatedException();
        }
        return new SimpleAuthenticationInfo(token, token, getName());
    }

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String uid = JwtUtil.getUsername(principals.toString());
        Account account = accountService.getByUserName(uid);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole(account.getIdentity());
        return simpleAuthorizationInfo;

    }
}
