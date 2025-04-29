package hey.car.event.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "hey.car.event.core",
        "hey.car.event.application",
        "hey.car.event.persistence",
        "hey.car.event.cache",
        "hey.car.event.message"
})
public class CarEventApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarEventApplication.class, args);
    }

}
