package com.bingo.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

//@Component
//@DefaultProperties(defaultFallback = "globalHysrix_fallbackmethod")
public class GlobalHysrix {

    public Map globalHysrix_fallbackmethod(){
        Map map = new HashMap();
        map.put("name", "系统繁忙,请稍后再试");
        return map;
    }
}
