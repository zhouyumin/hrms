package com.fwwb.hrms.shiro;

import java.util.logging.Logger;
/**
 * @Author: 黄天赐
 * @Date: Created in 10:41 2021/2/21
 * 
 */

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fwwb.hrms.po.Account;
import com.fwwb.hrms.service.AccountService;
@Service
public class MyRealm extends AuthorizingRealm{
	
    private static final Logger LOGGER = LogManager.getLogger(MyRealm.class);
    private AccountService accountService;
    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
        String uid = JWTUtil.getUsername(principals.toString());// TODO 自动生成的方法存根
        Account account=accountService.getByUserName(uid);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole(account.getIdentity());
        simpleAuthorizationInfo.addStringPermissions("添加具体权限");//添加具体权限list
        return simpleAuthorizationInfo;
		
	}
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO 自动生成的方法存根
		//拿到用户信息另一方式
//     UsernamePasswordToken  userToken=new UsernamePasswordToken();
//      String username=userToken.getUsername();
//      char[] password=userToken.getPassword();

          //获取用户的输入的用户名
          String uid = (String)token.getPrincipal();
          String password=(String)token.getCredentials();
          //通过username从数据库中查找 User对象
          Account account=accountService.getByUserName(uid);
         
          if(account == null){
        	  throw new UnknownAccountException();//报出未知用户异常
        	  
              
          } else if (!password.equals(account.getPassword())) {
              //密码错误
              throw new IncorrectCredentialsException();//密码错误异常
          }
          AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(uid, account.getPassword(), "my_realm" );//返回一个账号和密码
          return authenticationInfo;
	}
}
