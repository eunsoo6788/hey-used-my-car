package hey.car.event.core.coupon.service;


import hey.car.event.core.coupon.model.CouponCreateRequest;

public interface CouponService {

    void apply(CouponCreateRequest request);
}
