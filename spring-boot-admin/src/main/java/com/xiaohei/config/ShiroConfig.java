package com.xiaohei.config;

import com.xiaohei.config.filter.UserAuthenticatingFilter;
import com.xiaohei.matcher.AuthReam;
import com.xiaohei.matcher.PasswordMatcher;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : WiuLuS
 * @version : v1.11.28.2019
 * @discription :
 * @date : 2019-11-28 11:52:52
 * @email : m13886933623@163.com
 */
@Configuration
public class ShiroConfig {
    @Bean("securityManager")
    public SecurityManager securityManager(AuthReam ream) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(ream);
        securityManager.setRememberMeManager(new CookieRememberMeManager());
        return securityManager;
    }


    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager")SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);

        //oauth过滤
        Map<String, Filter> filters = new HashMap<>();
        filters.put("oauth2", new UserAuthenticatingFilter());
        shiroFilter.setFilters(filters);
        Map<String, String> filterMap = new LinkedHashMap<>();
        //这里配置不需要登录认证的请求地址
        filterMap.put("/business/user/login", "anon");
        filterMap.put("/business/user/logout", "anon");
        filterMap.put("/v2/api-docs", "anon");
        filterMap.put("/swagger-ui.html", "anon");
        filterMap.put("/swagger/**", "anon");
        filterMap.put("/swagger-resources/**", "anon");
        filterMap.put("/webjars/**", "anon");
        filterMap.put("/**", "oauth2");
        shiroFilter.setFilterChainDefinitionMap(filterMap);
        return shiroFilter;
    }




    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    @Bean("hashedCredentialsMatcher")
    public CredentialsMatcher credentialsMatcher(){
        PasswordMatcher matcher = new PasswordMatcher();
        //散列算法，这里使用md5
        matcher.setHashAlgorithmName("md5");
        //加密次数相当于md5(md5(md5(...)))
        matcher.setHashIterations(3);
        matcher.setStoredCredentialsHexEncoded(true);
        return matcher ;
    }

    @Bean
    public AuthReam authReam(CredentialsMatcher credentialsMatcher){
        AuthReam ream = new AuthReam();
        ream.setAuthorizationCachingEnabled(false);
        ream.setCredentialsMatcher(credentialsMatcher);
        return ream ;
    }
}
