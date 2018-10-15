package com.karolrinc.personservice.service;

import com.karolrinc.personservice.model.Person;
import com.karolrinc.personservice.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Page getPersonsPaged(int pageNumber, int pageSize, String sort) {
        return personRepository.findAll(PageRequest.of(pageNumber - 1, pageSize, Sort.by(sort).ascending()));
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }
}
