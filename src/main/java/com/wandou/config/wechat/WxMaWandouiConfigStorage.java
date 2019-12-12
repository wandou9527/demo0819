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

    @Override
    public boolean autoRefreshToken() {
        return false;
    }

    // TODO 考的xhc的配置实现方法到这里，18个方法，现在提示还有没实现的方法？难道是版本升级了吗？不一样了

}
