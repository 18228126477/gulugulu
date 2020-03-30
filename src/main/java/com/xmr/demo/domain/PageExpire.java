package com.xmr.demo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
public class PageExpire  implements Serializable {

    private Integer pageId;
    @JsonFormat(timezone = "GMT+8")
    private Date expireDate;

    @Override
    public String toString() {
        return "PageExpire{" +
                "pageId=" + pageId +
                ", expireDate=" + expireDate +
                '}';
    }
}