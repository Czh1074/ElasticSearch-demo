package com.example.comchenzhihuiesjd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 首页控制类
 * @Author: ChenZhiHui
 * @DateTime: 2023/5/16 14:59
 **/

@Controller
public class IndexController {

    @GetMapping({"/","/index"})
    public String hello(){
        return "index";
    }
}
