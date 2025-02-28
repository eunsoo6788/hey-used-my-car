package hey.car.event.core.coupon.service;

import hey.car.event.core.coupon.domain.Coupon;
import hey.car.event.core.coupon.model.CouponCreateRequest;
import hey.car.event.core.coupon.port.CouponPersistenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponPersistenceService implements CouponService {

    private final CouponPersistenceRepository persistenceRepository;

    @Override
    public void apply(CouponCreateRequest request) {
        var count = persistenceRepository.count();

        if (count > 100) {
            return;
        }

        var coupon = new Coupon(request.getUserId());
        persistenceRepository.save(coupon);

    }
}

