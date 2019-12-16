package com.wandou.config.wechat;

import cn.binarywang.wx.miniapp.config.WxMaConfig;
import com.wandou.config.wechat.properties.WxMaWandouiProperties;
import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: chh
 * @Description:
 * @Date: Created in 下午2:49 2018/1/9
 * @Modified:
 */
@Component
public class WxMaWandouiConfigStorage implements WxMaConfig {
    private String accessToken = null;

    protected Lock accessTokenLock = new ReentrantLock();

    //    @Autowired
//    RedisRepository mRedisRepository;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //微信小程序相关配置
    @Autowired
    private WxMaWandouiProperties wxMaWandouiProperties;

    @Override
    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public Lock getAccessTokenLock() {
        return accessTokenLock;
    }

    @Override
    public boolean isAccessTokenExpired() {
//        String token = mRedisRepository.getCache("WEIXINTOKEN:" + wxMaWandouiProperties.getAppId());
        String token = stringRedisTemplate.opsForValue().get("WEIXINTOKEN:" + wxMaWandouiProperties.getAppId());
        if (token == null) {
            return true;
        } else {
            this.accessToken = token;
            return false;
        }
    }

    @Override
    public void expireAccessToken() {
//        mRedisRepository.setCache("WEIXINTOKEN:" + wxMaWandouiProperties.getAppId(), "", 1); xhc mRedisTemplate.opsForValue().set(lkey, value, timeout, TimeUnit.MILLISECONDS);
        stringRedisTemplate.opsForValue().set("WEIXINTOKEN:" + wxMaWandouiProperties.getAppId(), "", 1, TimeUnit.MILLISECONDS);
        this.accessToken = null;
    }

    @Override
    public void updateAccessToken(WxAccessToken accessToken) {
        updateAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn());
    }

    @Override
    public void updateAccessToken(String accessToken, int expiresInSeconds) {
        this.accessToken = accessToken;
//        mRedisRepository.setCache("WEIXINTOKEN:" + wxMaWandouiProperties.getAppId(), accessToken, (expiresInSeconds - 200) * 1000);
        stringRedisTemplate.opsForValue().set("WEIXINTOKEN:" + wxMaWandouiProperties.getAppId(), accessToken, (expiresInSeconds - 200) * 1000, TimeUnit.MILLISECONDS);
    }

    // TODO 重新impl方法后增加的方法 start
    @Override
    public String getJsapiTicket() {
        return null;
    }

    @Override
    public Lock getJsapiTicketLock() {
        return null;
    }

    @Override
    public boolean isJsapiTicketExpired() {
        return false;
    }

    /**
     * 强制将jsapi ticket过期掉
     */
    @Override
    public void expireJsapiTicket() {

    }

    /**
     * 应该是线程安全的
     *
     * @param jsapiTicket      新的jsapi ticket值
     * @param expiresInSeconds 过期时间，以秒为单位
     */
    @Override
    public void updateJsapiTicket(String jsapiTicket, int expiresInSeconds) {

    }

    /**
     * 卡券api_ticket.
     */
    @Override
    public String getCardApiTicket() {
        return null;
    }

    @Override
    public Lock getCardApiTicketLock() {
        return null;
    }

    @Override
    public boolean isCardApiTicketExpired() {
        return false;
    }

    /**
     * 强制将卡券api ticket过期掉.
     */
    @Override
    public void expireCardApiTicket() {

    }

    /**
     * 应该是线程安全的.
     *
     * @param apiTicket        新的卡券api ticket值
     * @param expiresInSeconds 过期时间，以秒为单位
     */
    @Override
    public void updateCardApiTicket(String apiTicket, int expiresInSeconds) {

    }
    // TODO 重新impl方法后增加的方法 end

    @Override
    public String getAppid() {
        return wxMaWandouiProperties.getAppId();
    }

    @Override
    public String getSecret() {
        return wxMaWandouiProperties.getSecret();
    }

    @Override
    public String getToken() {
        return null;
    }

    @Override
    public String getAesKey() {
        return null;
    }

    @Override
    public String getMsgDataFormat() {
        return null;
    }

    @Override
    public long getExpiresTime() {
        // 过期时间动态配置在Redis里了？
//        return mRedisRepository.getCacheExpire("WEIXINTOKEN:" + wxMaWandouiProperties.getAppId());
        return DateUtils.MILLIS_PER_MINUTE * 30;
    }

    @Override
    public String getHttpProxyHost() {
        return null;
    }

    @Override
    public int getHttpProxyPort() {
        return 0;
    }

    @Override
    public String getHttpProxyUsername() {
        return null;
    }

    @Override
    public String getHttpProxyPassword() {
        return null;
    }

    @Override
    public ApacheHttpClientBuilder getApacheHttpClientBuilder() {
        return null;
    }

    // TODO 考的xhc的配置实现方法到这里，18个方法，现在提示还有没实现的方法？难道是版本升级了吗？不一样了
    // TODO public void updateAccessToken(String accessToken, int expiresInSeconds) { 为xhc版本的最后一个方法
    @Override
    public boolean autoRefreshToken() {
        return false;
    }

    // TODO 考的xhc的配置实现方法到这里，18个方法，现在提示还有没实现的方法？难道是版本升级了吗？不一样了

}
