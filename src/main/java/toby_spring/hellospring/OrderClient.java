package toby_spring.hellospring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import toby_spring.hellospring.order.Order;
import toby_spring.hellospring.order.OrderService;

import java.math.BigDecimal;

public class OrderClient {

    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(OrderConfig.class);
        OrderService orderService = beanFactory.getBean(OrderService.class);

        Order order = orderService.createOrder("0100", BigDecimal.TWO);
        System.out.println("order = " + order);
    }
}
