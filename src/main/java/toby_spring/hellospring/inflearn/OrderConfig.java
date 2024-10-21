package toby_spring.hellospring.inflearn;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import toby_spring.hellospring.inflearn.data.JdbcOrderRepository;
import toby_spring.hellospring.inflearn.order.OrderRepository;
import toby_spring.hellospring.inflearn.order.OrderService;
import toby_spring.hellospring.inflearn.order.OrderServiceImpl;

import javax.sql.DataSource;

@Configuration
@Import(DataConfig.class)
@EnableTransactionManagement
public class OrderConfig {

    @Bean
    public OrderService orderService(OrderRepository orderRepository) {
        return new OrderServiceImpl(orderRepository);
    }

    @Bean
    public OrderRepository orderRepository(DataSource dataSource) {
        return new JdbcOrderRepository(dataSource);
    }
}
