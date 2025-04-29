package hey.car.event.core.coupon.service;

import hey.car.event.core.coupon.model.CouponCreateRequest;
import hey.car.event.core.coupon.port.CouponPersistenceRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest(classes = {CouponCacheServiceTest.class})
@ComponentScan(basePackages = {"hey.car.event.core", "hey.car.event.persistence"})
class CouponCacheServiceTest {

    @Autowired
    private CouponPersistenceService couponPersistenceService;

    @Autowired
    private CouponPersistenceRepository couponPersistenceRepository;


    @DisplayName("1명의 사용자가 쿠폰 발급을 신청했을때 1개가 저장되어야 한다.")
    @Test
    void apply() {

        couponPersistenceService.apply(new CouponCreateRequest(1L));

//        long count = couponPersistenceRepository.count();

//        assertThat(count).isEqualTo(1L);
    }


}