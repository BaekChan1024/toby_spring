package toby_spring.hellospring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import toby_spring.hellospring.payment.Payment;
import toby_spring.hellospring.payment.PaymentService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

public class Client {

    public static void main(String[] args) throws IOException, InterruptedException {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(ObjectFactory.class);
        PaymentService paymentService = beanFactory.getBean(PaymentService.class);
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.valueOf(50.7));
        System.out.println("payment 1 : " + payment);

        TimeUnit.SECONDS.sleep(1);

        Payment payment2 = paymentService.prepare(1L, "USD", BigDecimal.valueOf(50.7));
        System.out.println("payment 2 : " + payment2);

        TimeUnit.SECONDS.sleep(2);

        Payment payment3 = paymentService.prepare(1L, "USD", BigDecimal.valueOf(50.7));
        System.out.println("payment 3 : " + payment3);
    }
}
