package lych.trucks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories({"lych.trucks.domain.repository"})
public class TrucksApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrucksApplication.class, args);
    }
}
