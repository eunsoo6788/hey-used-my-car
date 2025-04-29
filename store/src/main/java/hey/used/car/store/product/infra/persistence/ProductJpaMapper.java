package hey.used.car.store.product.infra.persistence;

import hey.used.car.store.product.domain.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductJpaMapper {

    ProductJpaMapper INSTANCE = Mappers.getMapper(ProductJpaMapper.class);

    ProductEntity toEntity(Product product);


}
