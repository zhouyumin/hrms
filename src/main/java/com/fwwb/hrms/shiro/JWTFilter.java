package com.fwwb.hrms.shiro;

import java.io.IOException;
import java.net.URLEncoder;

import javax.naming.AuthenticationException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;


import com.fwwb.hrms.utils.Constant;

public class JWTFilter extends BasicHttpAuthenticationFilter{
	
@Override
protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
    HttpServletRequest req = (HttpServletRequest) request;
    String authorization = req.getHeader(Constant.LOGIN);
    return authorization != null;
}

@Override
protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
    if (isLoginAttempt(request, response)) {
        try {
            executeLogin(request, response);
        } catch (Exception e) {
            throw new AuthorizationException("权限不足",e);
       }
    }
    return true;
}

@Override
protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
		// TODO 自动生成的方法存根
    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    String header = httpServletRequest.getHeader(Constant.LOGIN);

    JWTToken token = new JWTToken(header);
    // 提交给realm进行登入，如果错误他会抛出异常并被捕获
    getSubject(request, response).login(token);
    // 如果没有抛出异常则代表登入成功，返回true
    return true;
	}


}
