package com.wzs.shirospring.model;

import java.util.HashSet;
import java.util.Set;

/**
 *角色表
 */
public class Role {

    private Integer rid ;

    private String rname;

    private Set<Permission> permissions= new HashSet<>();

    private Set<User> userRoleSet = new HashSet<>();

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<User> getUserRoleSet() {
        return userRoleSet;
    }

    public void setUserRoleSet(Set<User> userRoleSet) {
        this.userRoleSet = userRoleSet;
    }
}
