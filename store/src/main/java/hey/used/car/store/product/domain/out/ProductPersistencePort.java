package hey.used.car.store.product.domain.out;

import hey.used.car.store.product.domain.entity.Product;

public interface ProductPersistencePort {
    void add(Product product);
}
