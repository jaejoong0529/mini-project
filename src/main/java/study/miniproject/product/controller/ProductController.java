package study.miniproject.product.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.miniproject.product.dto.request.ProductCreateRequest;
import study.miniproject.product.dto.request.ProductUpdateRequest;
import study.miniproject.product.dto.response.ProductCreateResponse;
import study.miniproject.product.dto.response.ProductDetailResponse;
import study.miniproject.product.dto.response.ProductListResponse;
import study.miniproject.product.service.ProductService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductCreateResponse> createProduct(
            @Valid @RequestBody ProductCreateRequest request
    ) {
        ProductCreateResponse response = productService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<ProductListResponse> getProducts(
            @PageableDefault(size = 10, sort = "createdAt", direction = Direction.DESC) Pageable pageable
    ) {
        ProductListResponse response = productService.getProducts(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDetailResponse> getProduct(
            @PathVariable Long productId
    ) {
        ProductDetailResponse response = productService.getProduct(productId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<Void> updateProduct(
            @PathVariable Long productId,
            @Valid @RequestBody ProductUpdateRequest request
    ) {
        productService.update(productId, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
