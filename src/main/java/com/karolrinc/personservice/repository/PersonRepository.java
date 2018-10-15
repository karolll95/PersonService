package com.karolrinc.personservice.repository;

import com.karolrinc.personservice.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
