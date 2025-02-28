package hey.car.event.core.coupon.port;

import hey.car.event.core.coupon.domain.Coupon;

public interface CouponPersistenceRepository {

    void save(Coupon coupon);

    Long count();


}
