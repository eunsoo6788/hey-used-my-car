package hey.car.event.application.coupon.service;

import hey.car.event.application.coupon.domain.Coupon;
import hey.car.event.application.coupon.infra.CouponJpaRepository;
import hey.car.event.application.coupon.infra.CouponRedisRepository;
import hey.car.event.application.producer.CouponCreateProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplyService {

    private final CouponJpaRepository couponJpaRepository;
    private final CouponRedisRepository couponRedisRepository;
    private final CouponCreateProducer couponCreateProducer;


    public void applyWithRaceCondition(Long userId) {

        var count = couponJpaRepository.count();

        if (count > 100) {
            return;
        }

        var coupon = new Coupon(userId);
        couponJpaRepository.save(coupon);

    }


    public void applyByRedis(Long userId) {

        var count = couponRedisRepository.increment();

        if (count > 100) {
            return;
        }

        var coupon = new Coupon(userId);
        couponJpaRepository.save(coupon);

    }


    public void applyByKafka(Long userId) {
        var count = couponRedisRepository.increment();

        if (count > 100) {
            return;
        }

        couponCreateProducer.create(userId);
    }

    public void applyByKafkaWithOneLimit(Long userId) {
        var success = couponRedisRepository.add(userId);

        if (success != 1) {
            return;
        }

        var count = couponRedisRepository.increment();

        if (count > 100) {
            return;
        }

        couponCreateProducer.create(userId);
    }


    public void create(Long userId) {
        var coupon = new Coupon(userId);

        couponJpaRepository.save(coupon);


        System.out.println(couponJpaRepository.count());
    }




}
