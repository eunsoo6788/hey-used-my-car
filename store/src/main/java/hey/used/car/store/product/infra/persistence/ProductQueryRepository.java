//package hey.used.car.store.product.infra.persistence;
//
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import hey.used.car.store.product.domain.enums.ProductStatus;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//
//import java.util.List;
//
//public class ProductQueryRepository {
//
//    private final JPAQueryFactory queryFactory;
//
//
//    public ProductQueryRepository(JPAQueryFactory queryFactory) {
//        this.queryFactory = queryFactory;
//    }
//
//
//
//    public Page<ProductEntity> findSellingProducts(Pageable pageable) {
//        List<ProductEntity> products = queryFactory
//                .selectFrom(productEntity)
//                .where(productEntity.status.eq(ProductStatus.SELLING))
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
//
//        // 전체 상품 개수 조회 (페이징을 위해 필요)
//        long total = queryFactory
//                .select(productEntity.count())
//                .from(productEntity)
//                .where(productEntity.status.eq(ProductStatus.SELLING))
//                .fetchOne();
//
//        return new PageImpl<>(products, pageable, total);
//    }
//}
