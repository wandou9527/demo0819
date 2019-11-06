package com.wandou.controller;

import com.wandou.annotation.XParam;
import com.wandou.cowspringbootstarter.dto.CowDTO;
import com.wandou.enums.XParamsType;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 2018-8-22
 */

@RestController
@RequestMapping(value = "index")
public class IndexController {
    @Autowired
    private CowDTO cowDTO;

    @RequestMapping("/index")
    public String index(@XParam(XParamsType.UID) Long uid) {
        System.out.println("有人访问！" + uid);
        return "欢迎光临！" +
                "今天是 " + DateFormatUtils.format(new Date(), "yyyy-MM-dd");
    }

//    @RequestMapping("/cow")
    @GetMapping("cow")
    public Object cowStarter(@RequestHeader(name = "userId", required = false) Long userId, @RequestAttribute(value = "userIdA", required = false) Long userIdA) {
        Map<String, Object> resp = new HashMap<>();
        resp.put("cow", cowDTO);
        resp.put("userId", userId);
        resp.put("userIdA", userIdA);
        return resp;
    }

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
}
