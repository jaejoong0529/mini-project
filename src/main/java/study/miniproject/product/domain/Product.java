package study.miniproject.product.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.miniproject.common.entity.BaseTimeEntity;

@Entity
@Table(name = "products")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 100)
    private String productName;

    @Column(nullable = false,length = 500)
    private String description;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false,length = 500)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Builder
    public Product(String productName, String description, Long price, Category category) {
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public static Product createProduct(String productName, String description, Long price, Category category) {
        return Product.builder()
                .productName(productName)
                .description(description)
                .price(price)
                .category(category)
                .build();
    }

    public void updateProduct(String productName, String description, Long price, Category category) {
        if (productName != null) this.productName = productName;
        if (description != null) this.description = description;
        if (price != null) this.price = price;
        if (category != null) this.category = category;
    }
}
