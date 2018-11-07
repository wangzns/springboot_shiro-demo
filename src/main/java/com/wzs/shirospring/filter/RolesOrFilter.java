package com.wzs.shirospring.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 自定义filter的实现角色的或者关系
 */
public class RolesOrFilter extends AuthorizationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        // 获得主体
        Subject subject = getSubject(servletRequest,servletResponse);
        String[] roles = (String[]) o;
        // 如果没有角色要求则通过
        if(roles==null || roles.length==0){
            return true;
        }
        for(String pr :roles){
            if(subject.hasRole(pr)){ // 如果有其中一个角色就返回true
                return true;
            }
        }
        return false;
    }



}
