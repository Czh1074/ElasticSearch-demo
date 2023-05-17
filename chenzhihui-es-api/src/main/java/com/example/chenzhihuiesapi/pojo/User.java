package com.example.chenzhihuiesapi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * User类
 * @Author: ChenZhiHui
 * @DateTime: 2023/5/16 13:05
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class User {
    private String name;
    private int age;
}
