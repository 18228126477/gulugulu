package com.xmr.demo.study.beanautowired;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public class BookDao {

    private String name = "时间管理";

    private BigDecimal price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BookDao{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
