package toby_spring.hellospring.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import toby_spring.hellospring.OrderConfig;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderConfig.class)
public class OrderServiceSpringTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private DataSource dataSource;

    @Test
    public void createOrder() {
        Order order = orderService.createOrder("0100", BigDecimal.TEN);
        Assertions.assertThat(order.getNo()).isEqualTo("0100");
        Assertions.assertThat(order.getTotal()).isEqualTo(BigDecimal.TEN);
        Assertions.assertThat(order.getId()).isGreaterThan(0);

    }

    @Test
    public void createOrders() {
        List<OrderReq> orderReqs = List.of(new OrderReq("0100", BigDecimal.TEN), new OrderReq("0200", BigDecimal.TWO));

        List<Order> orderList = orderService.createOrders(orderReqs);

        Assertions.assertThat(orderList.size()).isEqualTo(2);
        orderList.forEach(order -> Assertions.assertThat(order.getId()).isGreaterThan(0));
        Assertions.assertThat(orderList.get(0).getNo()).isEqualTo("0100");
        Assertions.assertThat(orderList.get(0).getTotal()).isEqualTo(BigDecimal.TEN);
        Assertions.assertThat(orderList.get(1).getNo()).isEqualTo("0200");
        Assertions.assertThat(orderList.get(1).getTotal()).isEqualTo(BigDecimal.TWO);
    }

    @Test
    public void createOrdersDuplicateNo() {
        List<OrderReq> orderReqs = List.of(new OrderReq("1500", BigDecimal.TEN), new OrderReq("1500", BigDecimal.TEN));
        assertThrows(DataIntegrityViolationException.class, () -> orderService.createOrders(orderReqs));
        JdbcClient client = JdbcClient.create(dataSource);
        var count = client.sql("select count(*) from orders where no = 'O100'").query(Long.class).single();
        assertThat(count).isEqualTo(0);
    }
}
