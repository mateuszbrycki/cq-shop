package com.cqshop.usermanagement.controller;

import com.cqshop.cqrs.common.validation.ValidationException;
import com.cqshop.usermanagement.application.command.RegisterAccountCommand;
import com.cqshop.web.ExceptionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Mateusz Brycki on 11/10/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class UserControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void shouldCheckEmptyPasswordAndMail() throws Exception {

        RegisterAccountCommand command = new RegisterAccountCommand("user", "", "");

        this.mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertToJSON(command)))
                .andExpect(status().isBadRequest())
        .andExpect(content().json(convertToJSON(new ExceptionResponse(new ValidationException("Password, username and email cannot be empty")))));
    }

    @Test
    public void shouldCheckEmptyMail() throws Exception {

        RegisterAccountCommand command = new RegisterAccountCommand("user", "password", "");

        this.mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertToJSON(command)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(convertToJSON(new ExceptionResponse(new ValidationException("Password, username and email cannot be empty")))));
    }

    @Test
    public void shouldCheckEmptyUsername() throws Exception {

        RegisterAccountCommand command = new RegisterAccountCommand("", "password", "mail");

        this.mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertToJSON(command)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(convertToJSON(new ExceptionResponse(new ValidationException("Password, username and email cannot be empty")))));
    }

    @Test
    public void shouldSaveUserSuccessfully() throws Exception {

        RegisterAccountCommand command = new RegisterAccountCommand("user", "password", "mail");

        this.mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertToJSON(command)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }


    @Test
    public void shouldSaveFirstUserAndRejectSecond() throws Exception {

        RegisterAccountCommand command = new RegisterAccountCommand("user1", "password1", "mail1");

        this.mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertToJSON(command)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        RegisterAccountCommand command1 = new RegisterAccountCommand("user2", "password2", "mail1");

        this.mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertToJSON(command1)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(convertToJSON(new ExceptionResponse(new ValidationException("User with mail1 already exists.")))));

    }

    private String convertToJSON(Object value) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(value);
    }
}