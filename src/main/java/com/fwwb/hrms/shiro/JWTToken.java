package com.fwwb.hrms.shiro;

import org.apache.shiro.authc.AuthenticationToken;
/**
 * @Author: 黄天赐
 * @Date: Created in 10:41 2021/2/21
 * 
 */
public class JWTToken implements AuthenticationToken {
		 
	    // 密钥
	    private String token;
	 
	    public JWTToken(String token) {
	        this.token = token;
	    }
	 
	    @Override
	    public Object getPrincipal() {
	        return token;
	    }
	 
	    @Override
	    public Object getCredentials() {
	        return token;
	    }
	}

