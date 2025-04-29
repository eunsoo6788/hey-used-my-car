package hey.used.car.store.product.infra.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hey.used.car.store.product.domain.enums.ProductStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {




}
