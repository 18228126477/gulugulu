package com.xmr.demo.domain;

import java.util.Date;

public class Character {
    private Integer id;
    private String name;
    private Integer sex;
    private String photoUrl;
    private String introduce;
    private String simpleIntroduce;
    private Integer statics;
    private String creater;
    private Date createDate;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getSex() {
        return sex;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getIntroduce() {
        return introduce;
    }

    public String getSimpleIntroduce() {
        return simpleIntroduce;
    }

    public Integer getStatics() {
        return statics;
    }

    public String getCreater() {
        return creater;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public void setSimpleIntroduce(String simpleIntroduce) {
        this.simpleIntroduce = simpleIntroduce;
    }

    public void setStatics(Integer statics) {
        this.statics = statics;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
