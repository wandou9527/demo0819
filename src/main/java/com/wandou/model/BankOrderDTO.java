package com.wandou.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 银行精选订单
 */

@Data
public class BankOrderDTO {
    private Long id;
    //银行资产编号
    private String bankAssetsNo;
    //银行编码
    private String bankCode;
    private String bankName;
    //0钱包 1悟空 channelCode 0 代表用户是在钱包购买的，1代表用户在悟空购买的
    private Integer channelCode;
    //渠道用户ID
    private String channelUserId;
    //到期日
    private Date expireTime;
    //持有金额 金额单位 元
    private BigDecimal holdingAmount;
    private String idCardNo;
    //起息日
    private Date interestTime;
    private String orderNo;
    //订单状态：0-持有中 1-回款中 3-支付确认中 4-已回款 5-已退款
    private Integer orderStatus;
    //期限描述
    private String periodDesc;
    private Long productId;
    private String productName;
    //收益率 4.2表示4.2%
    private BigDecimal profit;
    //购买金额 元
    private BigDecimal spendAmount;
    //购买时间
    private Date spendTime;

}
