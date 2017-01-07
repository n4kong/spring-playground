package com.muatik;

import com.muatik.model.Person;
import com.muatik.repository.PersonPagingAndSortingRepository;
import com.muatik.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;

/**
 * Created by muatik on 1/7/17.
 */

@SpringBootApplication
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    PersonPagingAndSortingRepository personPagingAndSortingRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner populateDB(PersonRepository repository) {
        return (args) -> {
            repository.save(new Person(75, "mustafa"));
            repository.save(new Person(74, "john"));
            repository.save(new Person(71, "jane"));
            repository.save(new Person(73, "neval"));
            repository.save(new Person(69, "micheal"));
            repository.save(new Person(81, "mateo"));

            logger.info("");
            logger.info("Count: " + repository.count());

            repository.findAllByHeight(71).forEach(each -> {
                logger.info(each.getName() + ", whose height is 71");
            });

            logger.info("== second page with page size 4 ==");
            personPagingAndSortingRepository.findAll(new PageRequest(1, 4)).forEach(each -> {
                logger.info(each.getName());
            });

        };
    }
}
