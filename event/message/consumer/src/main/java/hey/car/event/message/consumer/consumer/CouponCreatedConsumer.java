package hey.car.event.message.consumer.consumer;

import hey.car.event.application.coupon.service.ApplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CouponCreatedConsumer {

    private final ApplyService applyService;


    @KafkaListener(topics = "coupon_create", groupId = "group_1")
    public void listener(Long userId) {

        System.out.println(userId);
        applyService.create(userId);
    }


}
