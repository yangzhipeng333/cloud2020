package springcloud.controller;



import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import springcloud.service.PaymentService;

import javax.annotation.Resource;

@RestController
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

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
}
