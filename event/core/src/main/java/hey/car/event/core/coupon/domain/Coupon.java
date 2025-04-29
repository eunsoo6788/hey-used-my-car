package hey.car.event.core.coupon.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon {

    private Long id;

    private Long userId;


    public Coupon(Long userId) {
        this.userId = userId;
    }
}
