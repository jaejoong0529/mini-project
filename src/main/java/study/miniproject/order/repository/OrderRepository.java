package study.miniproject.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.miniproject.order.domain.Order;
import study.miniproject.order.exception.OrderNotFoundException;

public interface OrderRepository extends JpaRepository<Order, Long> {

    default Order getByIdOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> OrderNotFoundException.of(id));
    }
}
