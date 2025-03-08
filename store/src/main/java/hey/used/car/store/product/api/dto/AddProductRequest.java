package hey.used.car.store.product.api.dto;


import hey.used.car.store.product.domain.enums.DiscountPolicy;
import hey.used.car.store.product.domain.enums.ProductStatus;

public record AddProductRequest(

        String name,
        int price,
        int quantity,
        ProductStatus status,
        DiscountPolicy discountPolicy


) {

}
