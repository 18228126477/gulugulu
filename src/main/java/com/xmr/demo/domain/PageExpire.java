package com.xmr.demo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class PageExpire  implements Serializable {

    private static final long serialVersionUID = 6320554345271361329L;
    private Integer pageId;
    @JsonFormat(timezone = "GMT+8")
    private Date expireDate;

    public Integer getPageId() {
        return pageId;
    }

    public void setPageId(Integer pageId) {
        this.pageId = pageId;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    @Override
    public String toString() {
        return "PageExpire{" +
                "pageId=" + pageId +
                ", expireDate=" + expireDate +
                '}';
    }
}
