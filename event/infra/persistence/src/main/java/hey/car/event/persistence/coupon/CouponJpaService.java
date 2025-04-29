package hey.car.event.persistence.coupon;

import hey.car.event.core.coupon.domain.Coupon;
import hey.car.event.core.coupon.port.CouponPersistenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponJpaService implements CouponPersistenceRepository {

    private final CouponJpaRepository couponJpaRepository;

    @Override
    public void save(Coupon coupon) {
        couponJpaRepository.save(CouponJpaMapper.INSTANCE.toEntity(coupon));
    }

    @Override
    public Long count() {
        return couponJpaRepository.count();
    }
}
