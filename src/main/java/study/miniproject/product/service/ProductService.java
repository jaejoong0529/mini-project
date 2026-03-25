package study.miniproject.product.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.miniproject.product.domain.Product;
import study.miniproject.product.dto.request.ProductCreateRequest;
import study.miniproject.product.dto.request.ProductUpdateRequest;
import study.miniproject.product.dto.response.ProductCreateResponse;
import study.miniproject.product.dto.response.ProductDetailResponse;
import study.miniproject.product.dto.response.ProductListResponse;
import study.miniproject.product.exception.ProductNotFoundException;
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

    @Transactional(readOnly = true)
    public ProductListResponse getProducts(Pageable pageable) {
        Page<Product> page = productRepository.findAll(pageable);
        return ProductListResponse.from(page);
    }

    @Transactional(readOnly = true)
    public ProductDetailResponse getProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> ProductNotFoundException.of(productId));
        return ProductDetailResponse.from(product);
    }

    public void update(Long productId, ProductUpdateRequest request) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> ProductNotFoundException.of(productId));
        updateProduct(product, request);
    }

    public void delete(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> ProductNotFoundException.of(productId));
        productRepository.delete(product);
    }

    private Product createProduct(ProductCreateRequest request) {
        return Product.createProduct(request.productName(), request.description(), request.price(), request.category());
    }

    private void updateProduct(Product product, ProductUpdateRequest request) {
        product.updateProduct(request.productName(), request.description(), request.price(), request.category());
    }
}
