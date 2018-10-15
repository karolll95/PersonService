package com.karolrinc.personservice.controller;

import com.karolrinc.personservice.model.Person;
import com.karolrinc.personservice.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.ConstraintViolationException;
import java.util.List;

@Controller
@Slf4j
public class PersonViewController {

    private final PersonService personService;

    @Autowired
    public PersonViewController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public String getResults(@RequestParam(value = "page", defaultValue = "1") int page,
                             @RequestParam(value = "size", defaultValue = "1") int size,
                             @RequestParam(value = "sort", defaultValue = "id") String sort,
                             Model model) {
        try {
            Page personsPaged = personService.getPersonsPaged(page, size, sort);

            if (page > personsPaged.getTotalPages()) {
                String msg = "There is no more pages.";
                log.error(msg);
                model.addAttribute("errorMessage", msg);
                return "error";
            } else {
                model.addAttribute("person", Person.builder().build());
                model.addAttribute("page", page);
                model.addAttribute("selectedPageSize", size);
                model.addAttribute("pages", personsPaged);
                model.addAttribute("persons", (List<Person>) personsPaged.getContent());

                return "results";
            }
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        } catch (ConversionFailedException e) {
            String msg = "Wrong type: " + e.getSourceType() + ". Should be: " + e.getTargetType();
            log.error(e.getLocalizedMessage());
            model.addAttribute("errorMessage", msg);
            return "error";
        } catch (ConstraintViolationException e) {
            StringBuilder errorMessage = new StringBuilder();
            e.getConstraintViolations().forEach(v -> errorMessage.append(v.getMessage().concat(";")));
            log.error(errorMessage.toString());
            model.addAttribute("errorMessage", errorMessage);
            return "error";
        }
    }

    @PostMapping(path = "/persons")
    public String addPerson(@ModelAttribute("person") Person person, Model model) {
        model.addAttribute("person", personService.save(person));
        return "redirect:/";
    }
}
