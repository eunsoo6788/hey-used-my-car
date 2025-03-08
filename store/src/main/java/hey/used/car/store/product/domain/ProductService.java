package hey.used.car.store.product.domain;

import hey.used.car.store.product.api.dto.AddProductRequest;
import hey.used.car.store.product.domain.model.ProductMapper;
import hey.used.car.store.product.domain.out.ProductPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductPersistencePort persistencePort;

    public void add(AddProductRequest request) {
        var product = ProductMapper.INSTANCE.toDomain(request);
        persistencePort.add(product);
    }

}
