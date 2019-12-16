package com.wandou.service;

import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;

public interface WandouiWxService {

    // TODO xhc的授权接口 参数
    //    private String encryptData;
    //    private String iv;
    //    private String rawData;
    //    private String signature;
    //返回 userInfo: openId,unionId,nickname,headImgUrl,city,province,country,sex

    /**
     * @param code        临时登陆凭票
     * @param rawData     原始消息
     * @param signature   签名
     * @param encryptData 加密消息
     * @param ivs         向量
     * @return 用户信息
     */
    WxMaUserInfo getWechatOauthInfo(String code, String rawData, String signature, String encryptData, String ivs);


}
