package com.karolrinc.personservice.model;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Builder
@Data
@Entity
@Table(name = "persons", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Email
    private String email;

    @Type(type = "date")
    private Date birthDay;

}
