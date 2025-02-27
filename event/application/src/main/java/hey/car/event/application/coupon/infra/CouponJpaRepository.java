package hey.car.event.application.coupon.infra;

import hey.car.event.application.coupon.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponJpaRepository extends JpaRepository<Coupon, Long> {




}
