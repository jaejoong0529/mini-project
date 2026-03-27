package study.miniproject.order.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import study.miniproject.order.domain.Order;
import study.miniproject.order.exception.OrderNotFoundException;

public interface OrderRepository extends JpaRepository<Order, Long> {

    default Order getByIdOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> OrderNotFoundException.of(id));
    }

    @Query(
            value = "SELECT o FROM Order o JOIN FETCH o.product",
            countQuery="SELECT COUNT(o) FROM Order o"
    )
    Page<Order> findAllWithProduct(Pageable pageable);
}
