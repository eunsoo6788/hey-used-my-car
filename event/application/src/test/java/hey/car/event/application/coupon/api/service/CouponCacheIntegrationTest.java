package hey.car.event.application.coupon.api.service;

import hey.car.event.core.coupon.model.CouponCreateRequest;
import hey.car.event.core.coupon.port.CouponPersistenceRepository;
import hey.car.event.core.coupon.service.CouponCacheService;
import hey.car.event.core.coupon.service.CouponPersistenceService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CouponCacheIntegrationTest {

    @Autowired
    private CouponCacheService couponCacheService;

    @Autowired
    private CouponPersistenceRepository couponPersistenceRepository;


    @DisplayName("레디스를 사용하여 여러 명의 사용자가 쿠폰 발급을 신청했을때 정확하게 100개의 쿠폰만 발급된다.")
    @Test
    void multiApplyByRedis() throws InterruptedException {

        // given
        int threadCount = 1000;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);


        for (int i = 0 ; i < threadCount ; i++) {
            long userId = i ;
            var request = new CouponCreateRequest(userId);
            executorService.submit(() -> {
                try {
                    couponCacheService.apply(request);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        // when
        long count = couponPersistenceRepository.count();

        // then
        assertThat(count).isEqualTo(100L);
    }


}