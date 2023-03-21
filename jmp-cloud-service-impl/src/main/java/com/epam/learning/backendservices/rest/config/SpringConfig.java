package com.epam.learning.backendservices.rest.config;

import com.epam.learning.backendservices.rest.model.Subscription;
import com.epam.learning.backendservices.rest.model.User;
import com.epam.learning.backendservices.rest.repository.SubscriptionRepository;
import com.epam.learning.backendservices.rest.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class SpringConfig {

    private static final Logger log = LoggerFactory.getLogger(SpringConfig.class);

    @Bean
    public LinkDiscoverers discoverers() {
        List<LinkDiscoverer> plugins = new ArrayList<>();
        plugins.add(new CollectionJsonLinkDiscoverer());
        return new LinkDiscoverers(SimplePluginRegistry.of(plugins));

    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("My-API")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.epam.learning.backendservices.rest.controller.impl"))
                .paths(PathSelectors.any())
                .build();
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
                            "Anna",
                            "Bui",
                            LocalDate.of(1983,9,11)
                    )
            );
            log.info("Preloading {}", user2);
            var user3 = userRepository.save(
                    new User(null,
                            "Uliana",
                            "Bui",
                            LocalDate.of(2009,8,14)
                    )
            );
            log.info("Preloading {}", user3);
            var user4 = userRepository.save(
                    new User(null,
                            "Serafima",
                            "Bui",
                            LocalDate.of(2012,11,8)
                    )
            );
            log.info("Preloading {}", user4);
            var user5 = userRepository.save(
                    new User(null,
                            "Antony",
                            "Bui",
                            LocalDate.of(2016,1,31)
                    )
            );
            log.info("Preloading {}", user5);
            var user6 = userRepository.save(
                    new User(null,
                            "Elisha",
                            "Bui",
                            LocalDate.of(2022,6,22)
                    )
            );
            log.info("Preloading {}", user6);
            var subscription1 = subscriptionRepository.save(
                    new Subscription(null,
                            user1,
                            LocalDate.now().minusDays(1)
                    )
            );
            log.info("Preloading {}", subscription1);
            var subscription2 = subscriptionRepository.save(
                    new Subscription(null,
                            user2,
                            LocalDate.now().minusDays(2)
                    )
            );
            log.info("Preloading {}", subscription2);
            var subscription3 = subscriptionRepository.save(
                    new Subscription(null,
                            user3,
                            LocalDate.now().minusDays(3)
                    )
            );
            log.info("Preloading {}", subscription3);
            var subscription4 = subscriptionRepository.save(
                    new Subscription(null,
                            user4,
                            LocalDate.now().minusDays(4)
                    )
            );
            log.info("Preloading {}", subscription4);
            var subscription5 = subscriptionRepository.save(
                    new Subscription(null,
                            user5,
                            LocalDate.now().minusDays(5)
                    )
            );
            log.info("Preloading {}", subscription5);
            var subscription6 = subscriptionRepository.save(
                    new Subscription(null,
                            user6,
                            LocalDate.now().minusDays(6)
                    )
            );
            log.info("Preloading {}", subscription6);
        };
    }
}
