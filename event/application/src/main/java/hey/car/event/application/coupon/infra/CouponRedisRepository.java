package hey.car.event.application.coupon.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CouponRedisRepository {

    private final RedisTemplate<String,String> redisTemplate;


    public Long add(Long userId) {
        return redisTemplate
                .opsForSet()
                .add("applied_user", userId.toString());
    }

    public Long increment() {
        return redisTemplate.opsForValue()
                .increment("coupon_count");
    }

}
