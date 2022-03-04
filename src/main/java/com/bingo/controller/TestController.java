package com.bingo.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@DefaultProperties(defaultFallback = "globalHysrix_fallbackmethod")
public class TestController {

    @RequestMapping(value = "/query3")
    @ResponseBody
//    @HystrixCommand(fallbackMethod = "errorCallBack")
//    @HystrixCommand
    @HystrixCommand(fallbackMethod = "errorCallBack", commandProperties = {
//            @HystrixProperty(name ="execution.isolation.thread.timeoutInMilliseconds", value ="3000"),
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),//时间范围
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")//失败率多少后跳闸
    })
    public Map find11() throws InterruptedException {
        Map map = new HashMap();
        map.put("name", "hellow world");

//        Thread.sleep(5000);

        map.put("isFlag", true);
        if ((boolean) map.get("isFlag")) {
            throw new NullPointerException();
        }
        return map;
    }

    @RequestMapping(value = "/query4")
    @ResponseBody
//    @HystrixCommand(fallbackMethod = "errorCallBack")
//    @HystrixCommand
    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name ="execution.isolation.thread.timeoutInMilliseconds", value ="3000"),
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),//时间范围
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")//失败率多少后跳闸
    })
    public Map find22() {
        Map map = new HashMap();
        map.put("name", "hellow world");
        map.put("isFlag", true);
        return map;
    }

    public Map errorCallBack() {
        Map map = new HashMap();
        map.put("name", "降级。。。。2");
        return map;
    }

    public Map globalHysrix_fallbackmethod() {
        Map map = new HashMap();
        map.put("name", "系统繁忙,请稍后再试2");
        return map;
    }

}
