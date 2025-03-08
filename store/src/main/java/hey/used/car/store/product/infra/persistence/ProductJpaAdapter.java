package hey.used.car.store.product.infra.persistence;

import hey.used.car.store.product.domain.entity.Product;
import hey.used.car.store.product.domain.out.ProductPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class ProductJpaAdapter implements ProductPersistencePort {

    private final ProductJpaRepository repository;


    @Override
    public void add(Product product) {
        repository.save(ProductJpaMapper.INSTANCE.toEntity(product));
    }


}
