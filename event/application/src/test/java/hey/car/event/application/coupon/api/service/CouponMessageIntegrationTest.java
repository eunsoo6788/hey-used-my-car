package hey.car.event.application.coupon.api.service;

import hey.car.event.core.coupon.model.CouponCreateRequest;
import hey.car.event.core.coupon.port.CouponPersistenceRepository;
import hey.car.event.core.coupon.service.CouponMessageService;
import hey.car.event.core.coupon.service.CouponPersistenceService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CouponMessageIntegrationTest {

    @Autowired
    private CouponMessageService couponMessageService;

    @Autowired
    private CouponPersistenceRepository couponPersistenceRepository;



    @DisplayName("카프카를 사용하여 여러 명의 사용자가 쿠폰 발급을 신청했을때 한 사람당 하나의 쿠폰만 발급되어야 한다.")
    @Test
    void multiApplyByKafkaWithLimitOne() throws InterruptedException {

        // given
        var threadCount = 1000;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);


        for (int i = 0 ; i < threadCount ; i++) {
            long userId = i ;
            var request = new CouponCreateRequest(userId);
            executorService.submit(() -> {
                try {
                    couponMessageService.apply(request);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        // when
        TimeUnit.SECONDS.sleep(1); // consumer 동작 완료를 위한 대기
        var count = couponPersistenceRepository.count();

        // then
        assertThat(count).isEqualTo(1L);
    }



}