package hey.car.event.application.coupon.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CouponController {


    @PostMapping("/coupons")
    public void create() {

    }

}
