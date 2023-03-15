package com.epam.learning.backendservices.rest.config;

import com.epam.learning.backendservices.rest.model.Subscription;
import com.epam.learning.backendservices.rest.model.User;
import com.epam.learning.backendservices.rest.repository.SubscriptionRepository;
import com.epam.learning.backendservices.rest.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;
import java.time.LocalDate;

@Configuration
@EnableJpaRepositories(basePackageClasses = {
        UserRepository.class,
        SubscriptionRepository.class
})
public class SpringConfig {

    private static final Logger log = LoggerFactory.getLogger(SpringConfig.class);

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Bean
    public DataSource getDataSource() {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, SubscriptionRepository subscriptionRepository) {
        return args -> {
            var user1 = userRepository.save(
                    new User(null,
                            "Pavel",
                            "Bui",
                            LocalDate.of(1980,9,27)
                    )
            );
            log.info("Preloading {}", user1);
            var user2 = userRepository.save(
                    new User(null,
                            "Uliana",
                            "Bui",
                            LocalDate.of(2009,8,14)
                    )
            );
            log.info("Preloading {}", user2);
            var user3 = userRepository.save(
                    new User(null,
                            "Serafima",
                            "Bui",
                            LocalDate.of(2012,11,8)
                    )
            );
            log.info("Preloading {}", user3);
            var subscription1 = subscriptionRepository.save(
                    new Subscription(null,
                            user1,
                            LocalDate.now()
                    )
            );
            log.info("Preloading {}", subscription1);
            var subscription2 = subscriptionRepository.save(
                    new Subscription(null,
                            user2,
                            LocalDate.now()
                    )
            );
            log.info("Preloading {}", subscription2);
            var subscription3 = subscriptionRepository.save(
                    new Subscription(null,
                            user3,
                            LocalDate.now()
                    )
            );
            log.info("Preloading {}", subscription3);
        };
    }
}
