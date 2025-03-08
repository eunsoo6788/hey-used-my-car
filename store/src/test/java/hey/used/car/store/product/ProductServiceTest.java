package hey.used.car.store.product;

import hey.used.car.store.product.api.dto.AddProductRequest;
import hey.used.car.store.product.domain.enums.DiscountPolicy;
import hey.used.car.store.product.domain.ProductService;
import hey.used.car.store.product.domain.enums.ProductStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService service;

    @BeforeEach
    void setUp() {
    }


    @Test
    void get() {
    }

    @Test
    void gets() {
    }

    @Test
    @DisplayName("상품을 등록하다.")
    void add() {

        // given
        final var request =  new AddProductRequest("테스트", 1000, 1, ProductStatus.SELLING, DiscountPolicy.NONE);

        // when
        service.add(request);

        // then


    }


}