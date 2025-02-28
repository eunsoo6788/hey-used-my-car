package hey.car.event.persistence.coupon;

import hey.car.event.core.coupon.domain.Coupon;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CouponJpaMapper {

    CouponJpaMapper INSTANCE = Mappers.getMapper(CouponJpaMapper.class);

    CouponEntity toEntity(Coupon coupon);


}
