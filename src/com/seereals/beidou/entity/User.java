package com.seereals.beidou.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/4 0004.
 */
public class User implements Serializable {
    private Integer id;
    private String name;
    private String pwd;
    private String age;
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPws() {
        return pwd;
    }

    public void setPws(String pws) {
        this.pwd = pws;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
