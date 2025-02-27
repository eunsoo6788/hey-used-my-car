package hey.car.event.message.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"hey.car.event.message.consumer", "hey.car.event.application"})
public class CarEventConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarEventConsumerApplication.class, args);
    }

}
