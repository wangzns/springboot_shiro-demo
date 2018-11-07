package com.wzs.shirospring.realm;

import com.wzs.shirospring.model.Permission;
import com.wzs.shirospring.model.Role;
import com.wzs.shirospring.model.User;
import com.wzs.shirospring.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.HashSet;
import java.util.Set;

/**
 * 自定义realm
 */
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;


    /**
     * 权限校验
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        // 从session中取出对象
        String username = (String)principalCollection.getPrimaryPrincipal();
        User user = userService.findUserByUsername(username);
        if (user == null) {
            return null;
        }
        Set<String> permissions = new HashSet<>();
        Set<String> roles = new HashSet<>();
        Set<Role> roleSet = user.getRoleSet(); // 获取该用户的角色set
        if (CollectionUtils.isNotEmpty(roleSet)) {
            for (Role rl : roleSet) {   // 遍历角色set,取到权限set
                roles.add(rl.getRname());
                Set<Permission> permissionSet = rl.getPermissions();
                if (CollectionUtils.isNotEmpty(permissionSet)) { // 遍历权限set，取到用户具有的权限的名称
                    for (Permission pr : permissionSet) {
                        permissions.add(pr.getPname());
                    }
                }
            }
        }
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissions);
        simpleAuthorizationInfo.setRoles(roles);
        return simpleAuthorizationInfo;
    }

    /**
     * 登录认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 获取用户登录的用户名
        String username = (String) authenticationToken.getPrincipal();
        User user = userService.findUserByUsername(username);
        if (user == null) {
            return null;
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getUsername(),
                user.getPassword(), this.getClass().getName());
        return simpleAuthenticationInfo;
    }
}
