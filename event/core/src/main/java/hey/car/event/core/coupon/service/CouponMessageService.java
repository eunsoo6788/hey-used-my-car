package hey.car.event.core.coupon.service;

import hey.car.event.core.coupon.domain.Coupon;
import hey.car.event.core.coupon.model.CouponCreateRequest;
import hey.car.event.core.coupon.port.CouponCacheRepository;
import hey.car.event.core.coupon.port.CouponMessageSender;
import hey.car.event.core.coupon.port.CouponPersistenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
@RequiredArgsConstructor
public class CouponMessageService implements CouponService {

    private final CouponPersistenceRepository couponPersistenceRepository;
    private final CouponCacheRepository couponCacheRepository;
    private final CouponMessageSender couponMessageSender;

    @Override
    public void apply(CouponCreateRequest request) {

        var success = couponCacheRepository.add(request.getUserId());

        if (success != 1) {
            return;
        }

        var count = couponCacheRepository.increment();

        if (count > 100) {
            return;
        }

        couponMessageSender.send(request.getUserId());
    }


    public void create(Long userId) {
        var coupon = new Coupon(userId);

        couponPersistenceRepository.save(coupon);


        System.out.println(couponPersistenceRepository.count());
    }




}
