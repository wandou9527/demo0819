package com.wandou.feign;

import com.wandou.config.FeignConfig;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author liming
 * @date 2020/10/29
 * @description
 */

@FeignClient(name = "baidu", url = "http://baidu.com/", configuration = FeignConfig.class, primary = false)
public interface BaiduFeign {

    @RequestLine("GET /s?wd=java")
    String search();

}
