package study.miniproject.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.miniproject.product.domain.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
