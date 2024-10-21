package toby_spring.hellospring.inflearn.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import toby_spring.hellospring.inflearn.order.Order;
import toby_spring.hellospring.inflearn.order.OrderRepository;

public class JpaOrderRepository implements OrderRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Order order) {
        entityManager.persist(order);
    }
}
