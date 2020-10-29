package com.wandou.feign;


import com.wandou.config.FeignConfig;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 2020-10-29 feign终于成功了
 * 之前一直的失败，可以断定是jar冲突导致。因为昨天处理了冲突，今天feign就好使了
 * feign 接口仅需 @FeignClient
 * 主类仅需 @EnableFeignClients("com.wandou.feign")，无需spring组件扫描package
 */

@FeignClient(name = "mouseFeign", url = "http://wandour.top/mouse/", configuration = FeignConfig.class, primary = false)
public interface MouseFeign {

    @RequestLine("GET /c2/paris")
//    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Object c2Paris();

    @RequestLine("GET /matter_log/list")
    Object listMatterLog();
}
