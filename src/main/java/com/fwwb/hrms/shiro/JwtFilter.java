package com.fwwb.hrms.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;


/**
 * @Author: 黄天赐
 * @Date: Created in 10:41 2021/2/21
 */
public class JwtFilter extends BasicHttpAuthenticationFilter {

    // 验证是否携带token
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader("Authorization");
        return authorization != null;
    }

    //验证是否可访问
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isLoginAttempt(request, response)) {
            try {
                executeLogin(request, response);
            } catch (Exception e) {
                response401(response);
            }
        }
        return true;
    }

    //未携带token执行
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
        response401(response);
        return true;
    }

    //交给Realm执行登录
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String header = httpServletRequest.getHeader("Authorization");
        JwtToken token = new JwtToken(header);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        getSubject(request, response).login(token);
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    //返回401
    private void response401(ServletResponse resp) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
