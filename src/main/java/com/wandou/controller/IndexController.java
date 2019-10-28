package com.wandou.controller;

import com.wandou.cowspringbootstarter.dto.CowDTO;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 2018-8-22
 */

@RestController
@RequestMapping(value = "index")
public class IndexController {
    @Autowired
    private CowDTO cowDTO;

    @RequestMapping("/index")
    public String index() {
        System.out.println("有人访问！");
        return "欢迎光临！" +
                "今天是 " + DateFormatUtils.format(new Date(), "yyyy-MM-dd");
    }

    @GetMapping("cow")
    public CowDTO cowStarter() {
        return cowDTO;
    }
}
