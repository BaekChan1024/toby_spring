package toby_spring.hellospring.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import toby_spring.hellospring.order.Order;

import java.math.BigDecimal;

public class OrderRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Order save(Order order) {
        entityManager.persist(order);
        return order;
    }
}
