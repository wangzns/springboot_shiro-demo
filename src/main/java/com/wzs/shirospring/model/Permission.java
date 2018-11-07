package com.wzs.shirospring.model;


import java.util.HashSet;
import java.util.Set;

/**
 * 权限表
 */
public class Permission {

    private Integer pid;

    private String pname;

    private String url;


    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
