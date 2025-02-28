package hey.car.event.message.consumer.consumer;

import hey.car.event.core.coupon.service.CouponMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CouponCreatedConsumer {

    private final CouponMessageService couponMessageService;


    @KafkaListener(topics = "coupon_create", groupId = "group_1")
    public void listener(Long userId) {
        couponMessageService.create(userId);
    }


}
