package com.atguigu.springcloud.controller;



import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/payment/create")
    public CommonResult add(@RequestBody Payment payment){
      int result =  paymentService.add(payment);
      if (result == 1){
          return new CommonResult(200,"success",result);
      }else {
          return new CommonResult(401,"failed",result);
      }
    }

    @GetMapping("/payment/{id}")
    public CommonResult getPayment(@PathVariable("id") Long id){
        Payment payment = paymentService.getPayment(id);
        if (payment !=null){
            System.out.println("ssss");

            return new CommonResult(200,"success,serverport :"+serverPort,payment);
        }else {
            return new CommonResult(401,"failed,serverport :"+serverPort,null);
        }
    }

    @GetMapping("/payment/discovery")
    public Object Discovery(){
        List<String> services = discoveryClient.getServices();
        for (String ser: services) {
            System.out.println(ser);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("SPRINGCLOUD-PAYMENT-SERVICE");
        for (ServiceInstance ins:instances
             ) {
            System.out.println(ins.getServiceId()+"\t"+ins.getHost()+"\t"+ins.getPort()+"\t"+ins.getUri());
        }
    return "ok";
    }
}
