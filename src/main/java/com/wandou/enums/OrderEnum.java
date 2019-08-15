package com.wandou.enums;

/**
 * @author: liming
 * @date: 2019/7/23 11:04
 * @description:
 * @modify:
 */
public enum OrderEnum {
    ORDER_ENUM(0, "订单枚举");

    OrderEnum(int code, String desc) {
    }


    // 内部枚举
    public static enum TypeEnum {
        NORMAL(1, "常规订单"),
        GROUP(2, "团购订单");

        TypeEnum(int code, String desc) {
        }
    }

    public static enum StatusEnum {
        CREATE(1, "创建"),
        PRE_PAY(2, "发起支付"),
        PAY_SUCCESS(3, "支付成功");

        StatusEnum(int code, String desc) {
        }
    }

}
