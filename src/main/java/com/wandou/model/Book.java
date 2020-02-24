package com.wandou.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: liming
 * @date: 2019/7/12 13:50
 * @description:
 * @modify:
 */

@Data
public class Book implements Serializable {
    private String name;
    private Long price;

    public Book() {
    }

}
