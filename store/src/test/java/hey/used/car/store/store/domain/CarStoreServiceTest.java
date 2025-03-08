package hey.used.car.store.store.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarStoreServiceTest {

    @Test
    void get() {
    }

    @Test
    void gets() {
    }

    @Test
    void register() {
    }


    private class ProductService {

        final

        public void add() {
            throw new UnsupportedOperationException("Not supported yet.");
        }


    }

    private record AddProductRequest(

            String name,
            int price,
            int quantity,
            DiscountPolicy discountPolicy





    ) {}

    private enum DiscountPolicy {
        NONE
    }
}