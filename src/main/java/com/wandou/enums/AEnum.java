package com.wandou.enums;

/**
 * @author liming
 * @date 2018/11/20 10:05
 * @description 枚举内还可以写很多变量，有个银行信息的github上
 * @modify
 */
public enum AEnum {

    A(1, "英文中第一个字母"),
    B(2, "英文中第二个字母"),
    C(3, "英文中第三个字母");

    private int code;
    private String name;

    AEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

//    public static OrderStatusEnum getOrderStatusEnumByName(int status) {
//        for(OrderStatusEnum orderStatusEnum: values()) {
//            if(orderStatusEnum.getCode() == status) {
//                return orderStatusEnum;
//            }
//        }
//        return null;
//    }

    public static AEnum getByCode(int code) {
        for (AEnum a : values()) {
            if (a.code == code) {
                return a;
            }
        }
        return null;
    }
}
