package hey.car.event.application.coupon.api;

import hey.car.event.core.coupon.model.CouponCreateRequest;
import hey.car.event.core.coupon.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;

    @PostMapping("/coupons")
    public void create(@RequestBody CouponCreateRequest request) {
        couponService.apply(request);
    }

}
