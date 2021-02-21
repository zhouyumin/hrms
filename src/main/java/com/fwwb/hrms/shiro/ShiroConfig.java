package com.fwwb.hrms.shiro;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fwwb.hrms.utils.Constant;

@Configuration
public class ShiroConfig {
	 @Bean
	    public ShiroFilterFactoryBean factory(org.apache.shiro.mgt.SecurityManager securityManager) {
	        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
	        Map<String, Filter> filterMap = new HashMap<>();
	        //设置我们自定义的JWT过滤器
	        filterMap.put("jwt", new JWTFilter());
	        factoryBean.setFilters(filterMap);
	        factoryBean.setSecurityManager(securityManager);
	        // 设置无权限时跳转的 url;
	        factoryBean.setUnauthorizedUrl("/unauthorized/无权限");
	        Map<String, String> filterRuleMap = new HashMap<>();
	        //访问/login和/unauthorized 不需要经过过滤器
	        filterRuleMap.put(Constant.LOGIN, "anon");
	        filterRuleMap.put("/unauthorized/**", "anon");
	        // 所有请求通过我们自己的JWT Filter
	        filterRuleMap.put("/**", "jwt");
	        // 访问 /unauthorized/** 不通过JWTFilter
	        factoryBean.setFilterChainDefinitionMap(filterRuleMap);
	        return factoryBean;
	    }

	    /**
	     * 注入 securityManager
	     *
	     * @return the security manager
	     */
	    @Bean
	    public org.apache.shiro.mgt.SecurityManager securityManager() {
	        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
	        // 设置自定义 realm.
	        securityManager.setRealm(customRealm());
	        /*
	         * 关闭shiro自带的session，详情见文档
	         * http://shiro.apache.org/session-management.html#SessionManagement-StatelessApplications%28Sessionless%29
	         */
	        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
	        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
	        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
	        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
	        securityManager.setSubjectDAO(subjectDAO);
	        return securityManager;
	    }

	    @Bean
	    public MyRealm customRealm() {
	        return new MyRealm();
	    }

	    /**
	     * 开启shiro aop注解支持. 使用代理方式; 所以需要开启代码支持;
	     *
	     * @param securityManager 安全管理器
	     * @return 授权Advisor
	     */
	    @Bean
	    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(org.apache.shiro.mgt.SecurityManager securityManager) {
	        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
	        advisor.setSecurityManager(securityManager);
	        return advisor;
	    }
	}

