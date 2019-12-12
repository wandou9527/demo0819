package com.wandou.config.wechat.properties;

import lombok.Getter;
import org.springframework.context.annotation.Configuration;

/**
 * 豌豆i 小程序配置信息类
 */

@Getter
@Configuration
public class WxMaWandouiProperties {

    private String appId;

    private String secret;

}
