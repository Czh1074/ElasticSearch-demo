package com.example.comchenzhihuiesjd.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 信息检索类
 * @Author: ChenZhiHui
 * @DateTime: 2023/5/16 15:35
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Context {

    private String img;
    private String price;
    private String title;

}
