package com.wandou.enums;

/**
 * @author liming
 * @date 2018/12/5 15:03
 * @description
 * @modify
 */
public enum AlipayTradeStatusEnum {
    WAIT_BUYER_PAY("交易创建，等待买家付款"),
    TRADE_CLOSED("未付款交易超时关闭，或支付完成后全额退款"),
    TRADE_SUCCESS("交易支付成功"),
    TRADE_FINISHED("交易结束，不可退款");

    private String description;

    AlipayTradeStatusEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
