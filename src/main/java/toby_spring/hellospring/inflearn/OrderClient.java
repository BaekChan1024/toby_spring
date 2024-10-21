package toby_spring.hellospring.inflearn;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import toby_spring.hellospring.inflearn.order.Order;
import toby_spring.hellospring.inflearn.order.OrderService;

import java.math.BigDecimal;

public class OrderClient {

    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(OrderConfig.class);
        OrderService orderService = beanFactory.getBean(OrderService.class);

        Order order = orderService.createOrder("0100", BigDecimal.TWO);
        System.out.println("order = " + order);
    }
}
