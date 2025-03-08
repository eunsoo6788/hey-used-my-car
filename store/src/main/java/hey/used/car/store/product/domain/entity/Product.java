package hey.used.car.store.product.domain.entity;

import hey.used.car.store.product.domain.enums.DiscountPolicy;
import hey.used.car.store.product.domain.enums.ProductStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    private Long id;

    private String name;

    private int price;

    private int quantity;

    private ProductStatus status;

    private DiscountPolicy discountPolicy;


    public Product(String name, int price, int quantity, ProductStatus status, DiscountPolicy discountPolicy) {
        Assert.hasText(name, "상품명은 필수 입니다.");
        Assert.isTrue(price >= 0, "상품 가격은 0원 보다 커야 합니다.");

        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.discountPolicy = discountPolicy;
    }
}
