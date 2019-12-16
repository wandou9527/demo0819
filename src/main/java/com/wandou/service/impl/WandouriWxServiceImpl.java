package com.wandou.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.alibaba.fastjson.JSON;
import com.wandou.service.WandouiWxService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.annotation.Resource;

@Slf4j
@Service
public class WandouriWxServiceImpl implements WandouiWxService {

    @Resource(name = "wandouiMiniAppService")
    private WxMaService wxMaService;

    public void t() {
        WxMaUserInfo wxMaUserInfo = wxMaService.getUserService().getUserInfo("", "", "");
        log.info("wxMaUserInfo: {}", JSON.toJSONString(wxMaUserInfo));


    }


    @Override
    public WxMaUserInfo getWechatOauthInfo(String code, String rawData, String signature, String encryptData, String ivs) {
        try {
            WxMaJscode2SessionResult sessionInfo = wxMaService.getUserService().getSessionInfo(code);

            if (!wxMaService.getUserService().checkUserInfo(sessionInfo.getSessionKey(), rawData, signature)) {
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "user check failed");
            }

            WxMaUserInfo userInfo = wxMaService.getUserService().getUserInfo(sessionInfo.getSessionKey(), encryptData, ivs);

//            mRedisRepository.setCache(sKey + userInfo.getOpenId(), sessionInfo.getSessionKey(), (int) DateUtil.DAY * 7);
            return userInfo;
        } catch (WxErrorException e) {
            e.printStackTrace();
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
