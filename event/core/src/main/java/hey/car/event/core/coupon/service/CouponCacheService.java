package hey.car.event.core.coupon.service;

import hey.car.event.core.coupon.domain.Coupon;
import hey.car.event.core.coupon.model.CouponCreateRequest;
import hey.car.event.core.coupon.port.CouponCacheRepository;
import hey.car.event.core.coupon.port.CouponPersistenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponCacheService implements CouponService {

    private final CouponPersistenceRepository couponPersistenceRepository;
    private final CouponCacheRepository couponCacheRepository;

    @Override
    public void apply(CouponCreateRequest request) {
        var count = couponCacheRepository.increment();

        if (count > 100) {
            return;
        }

        var coupon = new Coupon(request.getUserId());
        couponPersistenceRepository.save(coupon);
    }
}
