package hey.car.event.cache.coupon;

import hey.car.event.core.coupon.port.CouponCacheRepository;
import hey.car.event.core.coupon.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CouponRedisRepository implements CouponCacheRepository {

    private final RedisTemplate<String,String> redisTemplate;


    @Override
    public Long add(Long userId) {
        return redisTemplate
                .opsForSet()
                .add("applied_user", userId.toString());
    }

    @Override
    public Long increment() {
        return redisTemplate.opsForValue()
                .increment("coupon_count");
    }

}
