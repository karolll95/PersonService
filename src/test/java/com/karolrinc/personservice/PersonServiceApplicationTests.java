package com.karolrinc.personservice;

import com.karolrinc.personservice.controller.PersonViewController;
import com.karolrinc.personservice.model.Person;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PersonServiceApplicationTests {

    @Autowired
    private PersonViewController personViewController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoads() {
        assertThat(personViewController).isNotNull();
    }

    @Test
    public void resultsGetTest() throws Exception {
        mockMvc.perform(get("/"))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(content().string(containsString("Persons")));
    }

    @Test
    public void resultsPostTest() throws Exception {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("firstName", "Brad");
        map.add("lastName", "Evans");
        map.add("email", "bevans@gmail.com");
        map.add("birthDay", "2001-10-01");

        mockMvc.perform(post("/persons").params(map)
                                             .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                    .andExpect(status().is3xxRedirection());
    }

}
