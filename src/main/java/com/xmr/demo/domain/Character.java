package com.xmr.demo.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
public class Character implements Serializable {
    private Integer id;
    private String name;
    private Integer sex;
    private String photoUrl;
    private String introduce;
    private String simpleIntroduce;
    private Integer statics;
    private String creator;
    private Date createDate;

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", photoUrl='" + photoUrl + '\'' +
                ", introduce='" + introduce + '\'' +
                ", simpleIntroduce='" + simpleIntroduce + '\'' +
                ", statics=" + statics +
                ", creator='" + creator + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
