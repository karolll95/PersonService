package com.karolrinc.personservice.util;


import com.karolrinc.personservice.model.Person;
import com.karolrinc.personservice.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Component
public class Initializer implements CommandLineRunner {


    private final PersonRepository personRepository;

    @Autowired
    public Initializer(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        this.personRepository.saveAll(Arrays.asList(Person.builder()
                                                          .firstName("Alan")
                                                          .lastName("Balan")
                                                          .email("alan@op.pl")
                                                          .birthDay(Date.valueOf(LocalDate.of(2000, 12, 12)))
                                                          .build(), Person.builder()
                                                                          .firstName("John")
                                                                          .lastName("Rambo")
                                                                          .email("johnrambo@gmail.com")
                                                                          .birthDay(Date.valueOf(LocalDate.now()))
                                                                          .build(), Person.builder()
                                                                                          .firstName("James")
                                                                                          .lastName("Bond")
                                                                                          .email("jamesbond@gmail.com")
                                                                                          .birthDay(Date.valueOf(LocalDate.now()))
                                                                                          .build()));
    }
}
