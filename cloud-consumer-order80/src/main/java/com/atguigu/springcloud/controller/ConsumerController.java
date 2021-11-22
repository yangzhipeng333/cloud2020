package com.atguigu.springcloud.controller;



import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class ConsumerController {

    @Resource
    private RestTemplate restTemplate ;


    public static final String url= "http://SPRINGCLOUD-PAYMENT-SERVICE";

   @GetMapping("/consumer/create")
    public CommonResult<Payment> add(Payment payment){
        return restTemplate.postForObject(url+"/payment/create",payment,CommonResult.class);
    }


    @GetMapping("/consumer/{id}")
    public CommonResult<Payment> getInfo(@PathVariable("id") Long id){
        return restTemplate.getForObject(url+"/payment/"+id,CommonResult.class);
    }

}
