package hey.car.event.message.producer.producer;

import hey.car.event.core.coupon.port.CouponMessageSender;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaMessageProducer implements CouponMessageSender {

    private final KafkaTemplate<String, Long> kafkaTemplate;

    @Override
    public void send(Long userId) {
        kafkaTemplate.send("coupon_create",userId);
    }


}
