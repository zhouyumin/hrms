package com.fwwb.hrms.shiro;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.Filter;

import com.fwwb.hrms.shiro.AccountRealm;
import com.fwwb.hrms.shiro.jwt.JwtFilter;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fwwb.hrms.utils.Constant;

/**
  @Author: 黄天赐
 * @Date: Created in 10:41 2021/2/21
 */

/*
 * shiro启用注解拦截控制器
 */
@Configuration
public class ShiroConfig {
    @Resource
    JwtFilter jwtFilter;

    @Resource
    RedisSessionDAO redisSessionDAO;

    @Resource
    RedisCacheManager redisCacheManager;

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        Map<String, Filter> filterMap = new HashMap<>();
        //设置我们自定义的JWT过滤器
        filterMap.put("jwt", jwtFilter);
        factoryBean.setFilters(filterMap);
        factoryBean.setSecurityManager(securityManager);

        Map<String, String> filterRuleMap = new HashMap<>();
        //访问/login和/unauthorized 不需要经过过滤器
        filterRuleMap.put(Constant.LOGIN, "anon");
        filterRuleMap.put(Constant.REGISTER, "anon");
        // 所有请求通过我们自己的JWT Filter
        filterRuleMap.put("/**", "jwt");
        factoryBean.setFilterChainDefinitionMap(filterRuleMap);
        return factoryBean;
    }


    @Bean
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO);
        return sessionManager;
    }

    /**
     * 注入 securityManager
     *
     * @return the security manager
     */
    @Bean
    public DefaultWebSecurityManager securityManager(AccountRealm accountRealm,
                                           SessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        // 设置自定义 realm.
        //开启认证和授权缓存
        accountRealm.setAuthenticationCachingEnabled(true);
        accountRealm.setAuthorizationCachingEnabled(true);
        //设置缓存管理器
        accountRealm.setCacheManager(redisCacheManager);
        securityManager.setRealm(accountRealm);

        //设置会话管理
//        securityManager.setSessionManager(sessionManager);
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

    //// 设置代理不然返回结果都是404
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        // 强制使用cglib，防止重复代理和可能引起代理出错的问题
        // https://zhuanlan.zhihu.com/p/29161098
         defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);

        // https://blog.csdn.net/shmilyhq/article/details/113151920
        // defaultAdvisorAutoProxyCreator.setUsePrefix(true);
        return defaultAdvisorAutoProxyCreator;
    }

}

