package com.wandou.model.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员二期 权益
 * 用是否领取排序，0未领取在前，已领取以领取时间正序，未领取以生效时间倒序
 */

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class MemberPrivilegeDTO implements Serializable, Comparable<MemberPrivilegeDTO> {
    private static final long serialVersionUID = 1L;

    private Long id;

    //0悟空 1钱包
    private Integer channel;

    private String masterTitle;

    private String subTitle;

    private String imgUrl;

    private String descImgUrl;

    private Date effectStart;

    private Date effectEnd;

    //0 生效中(待生效) 1 生效 2 失效
    private Integer effectStatus;

    private Integer contentType;

    private String contentId;

    private String lvcodes;

    private String remark;

    private Integer skipType;

    private String skipUrl;

    private Integer isDelete;

    private Date createTime;

    private Date updateTime;

    private String headUrl;

    private String headName;

    private Integer isReceive;//0 未领取 1 已领取

    private Integer isRight;//0 有权限 1 无权限


    @Override
    public int compareTo(MemberPrivilegeDTO o) {
        return 0;
    }
}