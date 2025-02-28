package hey.car.event.application.coupon.api.service;

import hey.car.event.core.coupon.model.CouponCreateRequest;
import hey.car.event.core.coupon.port.CouponPersistenceRepository;
import hey.car.event.core.coupon.service.CouponPersistenceService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

// todo. test profile만 동작하도록 설정
@SpringBootTest
class CouponPersistenceIntegrationTest {

    @Autowired
    private CouponPersistenceService couponPersistenceService;

    @Autowired
    private CouponPersistenceRepository couponPersistenceRepository;


    @DisplayName("1명의 사용자가 쿠폰 발급을 신청했을때 1개가 저장되어야 한다.")
    @Test
    void apply() {

        couponPersistenceService.apply(new CouponCreateRequest(2L));

        long count = couponPersistenceRepository.count();

        assertThat(count).isEqualTo(2L);
    }


    @DisplayName("여러 명의 사용자가 쿠폰 발급을 신청했을때 동시성 이슈로 인해 100개 보다 많은 쿠폰이 발급된다.")
    @Test
    void multiApplyWithRaceCondition() throws InterruptedException {

        int threadCount = 1000;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);


        for (int i = 0 ; i < threadCount ; i++) {
            long userId = i ;
            var request = new CouponCreateRequest(userId);
            executorService.submit(() -> {
                try {
                    couponPersistenceService.apply(request);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        long count = couponPersistenceRepository.count();

        assertThat(count).isNotEqualTo(100L);
    }


}