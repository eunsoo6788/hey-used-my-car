package hey.used.car.store.product.domain.model;

import hey.used.car.store.product.api.dto.AddProductRequest;
import hey.used.car.store.product.domain.entity.Product;
import hey.used.car.store.product.infra.persistence.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product toDomain(AddProductRequest request);


}
