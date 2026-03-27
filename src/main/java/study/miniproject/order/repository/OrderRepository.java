package study.miniproject.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.miniproject.order.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
