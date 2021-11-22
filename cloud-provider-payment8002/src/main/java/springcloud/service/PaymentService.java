package springcloud.service;


import com.atguigu.springcloud.entities.Payment;
import org.springframework.stereotype.Service;
import springcloud.dao.PaymentDao;

import javax.annotation.Resource;

@Service
public class PaymentService {

    @Resource
    private PaymentDao paymentDao;

    public int add(Payment payment){
      int result=  paymentDao.add(payment);
      return result;
    }

    public Payment getPayment(Long id){
        Payment payment = paymentDao.getPaymentById(id);
        return payment;
    }

}
