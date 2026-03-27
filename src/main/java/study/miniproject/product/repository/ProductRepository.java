package study.miniproject.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.miniproject.product.domain.Product;
import study.miniproject.product.exception.ProductNotFoundException;

public interface ProductRepository extends JpaRepository<Product,Long> {

    default Product getByIdOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> ProductNotFoundException.of(id));
    }
}
