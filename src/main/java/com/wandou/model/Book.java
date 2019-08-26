package com.wandou.model;

import java.io.Serializable;

/**
 * @author: liming
 * @date: 2019/7/12 13:50
 * @description:
 * @modify:
 */

public class Book implements Serializable {
    private String name;
    private Long price;

    public Book() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
