package com.wandou.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *  member_privilege
 * @author 大狼狗 2019-10-29
 */
@Data
public class MemberPrivilege implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id = 1L;

    /**
     * 主标题
     */
    private String masterTitle = "";

    /**
     * 副标题
     */
    private String subTitle = "";

    /**
     * 图片地址
     */
    private String imgUrl = "";

    /**
     * 权益详情图片地址
     */
    private String descImgUrl = "";

    /**
     * 生效时间开始
     */
    private Date effectStart = new Date();

    /**
     * 生效时间结束
     */
    private Date effectEnd = new Date();

    /**
     * 0 生效中 1 生效 2 失效
     */
    private Integer effectStatus = 1;

    /**
     * 1 卡券，2 积分
     */
    private Integer contentType = 1;

    /**
     * 权益内容id
     */
    private String contentId = "";

    /**
     * 白名单id
     */
    private Long whitelistId = 1L;

    /**
     * lv0、lv1、lv2、lv3、lv4、lv5
     */
    private String lvcodes = "";

    /**
     * 备注
     */
    private String remark = "";

    /**
     * 0-正常 1-删除
     */
    private Integer isDelete = 0;

    /**
     * 创建时间
     */
    private Date createTime = new Date();

    /**
     * 更新时间
     */
    private Date updateTime = new Date();

    public MemberPrivilege() {
    }

}
