package study.miniproject.product.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.miniproject.product.dto.request.ProductCreateRequest;
import study.miniproject.product.dto.response.ProductCreateResponse;
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
}
