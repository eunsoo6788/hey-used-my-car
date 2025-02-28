//package hey.car.event.application.coupon.api.service;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.TimeUnit;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//class CouponServiceTest {
//
//    @Autowired
//    private CouponServiceImpl couponServiceImpl;
//
//    @Autowired
//    private CouponJpaRepository couponJpaRepository;
//
//
//
//
//    @DisplayName("1명의 사용자가 쿠폰 발급을 신청했을때 1개가 저장되어야 한다.")
//    @Test
//    void singleApply() {
//        couponServiceImpl.applyWithRaceCondition(1L);
//
//        long count = couponJpaRepository.count();
//
//        assertThat(count).isEqualTo(1L);
//    }
//
//
//    @DisplayName("여러 명의 사용자가 쿠폰 발급을 신청했을때 동시성 이슈로 인해 100개 보다 많은 쿠폰이 발급된다.")
//    @Test
//    void multiApplyWithRaceCondition() throws InterruptedException {
//
//        int threadCount = 1000;
//        ExecutorService executorService = Executors.newFixedThreadPool(32);
//        CountDownLatch latch = new CountDownLatch(threadCount);
//
//
//        for (int i = 0 ; i < threadCount ; i++) {
//            long userId = i ;
//            executorService.submit(() -> {
//                try {
//                    couponServiceImpl.applyWithRaceCondition(userId);
//                } finally {
//                    latch.countDown();
//                }
//            });
//        }
//
//        latch.await();
//
//        long count = couponJpaRepository.count();
//
//        assertThat(count).isNotEqualTo(100L);
//    }
//
//
//    @DisplayName("레디스를 사용하여 여러 명의 사용자가 쿠폰 발급을 신청했을때 정확하게 100개의 쿠폰만 발급된다.")
//    @Test
//    void multiApplyByRedis() throws InterruptedException {
//
//        // given
//        int threadCount = 1000;
//        ExecutorService executorService = Executors.newFixedThreadPool(32);
//        CountDownLatch latch = new CountDownLatch(threadCount);
//
//
//        for (int i = 0 ; i < threadCount ; i++) {
//            long userId = i ;
//            executorService.submit(() -> {
//                try {
//                    couponServiceImpl.applyByRedis(userId);
//                } finally {
//                    latch.countDown();
//                }
//            });
//        }
//
//        latch.await();
//
//        // when
//        long count = couponJpaRepository.count();
//
//        // then
//        assertThat(count).isEqualTo(100L);
//    }
//
//
//
//    @DisplayName("카프카를 사용하여 여러 명의 사용자가 쿠폰 발급을 신청했을때 정확하게 100개의 쿠폰만 발급된다.")
//    @Test
//    void multiApplyByKafka() throws InterruptedException {
//
//        // given
//        int threadCount = 1000;
//        ExecutorService executorService = Executors.newFixedThreadPool(32);
//        CountDownLatch latch = new CountDownLatch(threadCount);
//
//
//        for (int i = 0 ; i < threadCount ; i++) {
//            long userId = i ;
//            executorService.submit(() -> {
//                try {
//                    couponServiceImpl.applyByKafka(userId);
//                } finally {
//                    latch.countDown();
//                }
//            });
//        }
//
//        latch.await();
//
//        // when
//        TimeUnit.SECONDS.sleep(1); // consumer 동작 완료를 위한 대기
//        long count = couponJpaRepository.count();
//
//        // then
//        assertThat(count).isEqualTo(100L);
//    }
//
//
//
//    @DisplayName("카프카를 사용하여 여러 명의 사용자가 쿠폰 발급을 신청했을때 한 사람당 하나의 쿠폰만 발급되어야 한다.")
//    @Test
//    void multiApplyByKafkaWithLimitOne() throws InterruptedException {
//
//        // given
//        var threadCount = 1000;
//        ExecutorService executorService = Executors.newFixedThreadPool(32);
//        CountDownLatch latch = new CountDownLatch(threadCount);
//
//
//        for (int i = 0 ; i < threadCount ; i++) {
//            long userId = 1 ;
//            executorService.submit(() -> {
//                try {
//                    couponServiceImpl.applyByKafkaWithOneLimit(userId);
//                } finally {
//                    latch.countDown();
//                }
//            });
//        }
//
//        latch.await();
//
//        // when
//        TimeUnit.SECONDS.sleep(1); // consumer 동작 완료를 위한 대기
//        var count = couponJpaRepository.count();
//
//        // then
//        assertThat(count).isEqualTo(1L);
//    }
//
//
//}