package lych.trucks.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Config for Dozer.
 */
@Configuration
public class DozerConfig {

    @Bean
    public DozerBeanMapper mapper() {

        return new DozerBeanMapper();
    }
}
