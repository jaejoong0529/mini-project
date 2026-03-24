package study.miniproject.product.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.miniproject.product.domain.Product;
import study.miniproject.product.dto.request.ProductCreateRequest;
import study.miniproject.product.dto.response.ProductCreateResponse;
import study.miniproject.product.repository.ProductRepository;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    public ProductCreateResponse create(ProductCreateRequest request) {
        Product product = createProduct(request);
        Product savedProduct = productRepository.save(product);
        return new ProductCreateResponse(savedProduct.getId());
    }

    private Product createProduct(ProductCreateRequest request) {
        return Product.createProduct(request.productName(),request.description(),request.price(),request.category());
    }
}
