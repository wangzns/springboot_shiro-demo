package com.wzs.shirospring.config;


import com.wzs.shirospring.CredentialMatcher;
import com.wzs.shirospring.filter.RolesOrFilter;
import com.wzs.shirospring.realm.CustomRealm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfiguration {



    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager,
                                                         @Qualifier("rolesOrFilter") RolesOrFilter rolesOrFilter ){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);
        bean.setLoginUrl("/login");
        bean.setSuccessUrl("/index");
        bean.setUnauthorizedUrl("/unauthorized");
        // 设置自定义filter
        Map<String,Filter> customFilterMap = new HashMap<>();
        customFilterMap.put("rolesOr",rolesOrFilter);
        bean.setFilters(customFilterMap);

        // 设置过滤链
        Map<String,String> filterMap = new HashMap<String,String>();
        filterMap.put("/login","anon"); // 无需认证
        filterMap.put("/loginUser","anon"); // 无需认证
        filterMap.put("/druid/**","anon"); // 无需认证(druid监控)
//        filterMap.put("/admin","roles[admin,normal]"); // 需要有admin和normal角色认证通过
        filterMap.put("/admin","rolesOr[admin,normal]"); // 使用自定义的filter (有其中一个角色认证通过)
        filterMap.put("/edit","perms[edit,query]"); // 需要有edit和query权限认证通过
        filterMap.put("/**","user");// 登录即认证通过
        bean.setFilterChainDefinitionMap(filterMap);
        return bean;
    }

    /**
     * 自定义filter
     * @return
     */
    @Bean("rolesOrFilter")
    public RolesOrFilter rolesOrFilter(){
        return new RolesOrFilter();
    }

    @Bean("securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("customRealm") CustomRealm realm,
                                                               @Qualifier("cookieRememberMeManager") CookieRememberMeManager cookieRememberMeManager){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        securityManager.setRememberMeManager(cookieRememberMeManager); // 记住我
        return securityManager;
    }


    @Bean("customRealm")
    public CustomRealm customRealm(@Qualifier("credentialMatcher") CredentialMatcher matcher){
        CustomRealm customRealm = new CustomRealm();
        customRealm.setCredentialsMatcher(matcher);
        return customRealm;
    }

    @Bean("credentialMatcher")
    public CredentialMatcher credentialMatcher(){
        return new CredentialMatcher();
    }


    @Bean("cookieRememberMeManager")
    public CookieRememberMeManager cookieRememberMeManager(@Qualifier("cookie") SimpleCookie cookie ){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(cookie);
        return cookieRememberMeManager;
    }

    @Bean("cookie")
    public SimpleCookie simpleCookie(){
        SimpleCookie cookie = new SimpleCookie();
        cookie.setName("remenberme"); // 设置cookie名称
        cookie.setMaxAge(200); // 设置cookie最大存活时间，单位为s
        return cookie;
    }


    /*自定义shiro与spring的关系*/

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }



}
