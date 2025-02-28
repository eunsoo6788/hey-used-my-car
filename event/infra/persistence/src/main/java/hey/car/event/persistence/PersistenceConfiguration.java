package hey.car.event.persistence;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "hey.car.event.persistence")
@EntityScan(basePackages = "hey.car.event.persistence")  // 여기에 엔티티가 있는 패키지 추가
public class PersistenceConfiguration {
}
