package com.example.comchenzhihuiesjd.controller;

import com.example.comchenzhihuiesjd.service.ContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 内容控制类
 * @Author: ChenZhiHui
 * @DateTime: 2023/5/16 15:45
 **/
@RestController
public class ContextController {

    @Autowired
    private ContextService contextService;

    // 收集数据
    @GetMapping("/parse/{keyword}")
    public Boolean parse(@PathVariable("keyword") String keyword) throws IOException {
        return contextService.parseContext(keyword);
    }

    // 获取数据，并检索
    @GetMapping("/search/{keyword}/{pageNo}/{pageSize}")
    public List<Map<String,Object>> search( @PathVariable("keyword") String keyword,
                                            @PathVariable("pageNo") int pageNo,
                                            @PathVariable("pageSize") int pageSize) throws IOException {
        return contextService.searchPage(keyword,pageNo,pageSize);

    }

}
