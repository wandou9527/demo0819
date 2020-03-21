package com.wandou.controller;

import com.wandou.annotation.XParam;
//import com.wandou.cowspringbootstarter.dto.CowDTO;
import com.wandou.enums.XParamsType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 2018-8-22
 */

@Slf4j
@RestController
@RequestMapping(value = "index")
public class IndexController {
//    @Autowired
//    private CowDTO cowDTO;

    @RequestMapping("/index")
    public String index(@XParam(XParamsType.UID) Long uid) {
        log.info("有人访问！{}", uid);
        return "欢迎光临！" +
                "今天是 " + DateFormatUtils.format(new Date(), "yyyy-MM-dd");
    }

//    @GetMapping("cow")
//    public Object cowStarter(@RequestHeader(name = "userId", required = false) Long userId, @RequestAttribute(value = "userIdA", required = false) Long userIdA) {
//        Map<String, Object> resp = new HashMap<>();
//        resp.put("cow", cowDTO);
//        resp.put("userId", userId);
//        resp.put("userIdA", userIdA);
//        return resp;
//    }

    /**
     * XParam实验
     *
     * @param uid
     * @return
     */
    @GetMapping("xparam")
    public Object t2(@XParam(XParamsType.UID) Long uid) {
        Long uid2 = uid;
        return uid2;
    }

    @PostMapping("/m3")
    public Object m3() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String data = request.getParameter("data");
        return data;
    }

    /**
     * 测试阿里云tool部署服务器
     * 2020-02-24
     *
     * @param param
     * @return
     */
    @GetMapping("/ali_tool")
    public Object m4(@RequestParam(required = false) String param) {
        return "ali_tool" + param;
    }

    /**
     * 随机数
     *
     * @param num
     * @return
     */
    @GetMapping("/random_numeric")
    public String randomNumeric(@RequestParam(required = false, defaultValue = "6") Integer num) {
        return "random_numeric: " + RandomStringUtils.randomNumeric(num);
    }


}
