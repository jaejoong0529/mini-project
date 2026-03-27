package study.miniproject.order.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.miniproject.common.entity.BaseTimeEntity;
import study.miniproject.product.domain.Product;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Long totalPrice;

    @Builder
    public Order(Product product, Integer quantity, Long totalPrice) {
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public static Order createOrder(Product product, Integer quantity) {
        long totalPrice = product.getPrice() * quantity;
        return Order.builder()
                .product(product)
                .quantity(quantity)
                .totalPrice(totalPrice)
                .build();
    }
}
