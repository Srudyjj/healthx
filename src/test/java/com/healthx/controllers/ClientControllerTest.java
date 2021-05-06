package com.healthx.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ClientControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void addClient() throws Exception {
        mvc.perform(
                post("/user")
                    .with(httpBasic("john", "12345"))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content("{" +
                            "\"username\": \"petro\"," +
                            "\"password\": \"password\"" +
                            "}")
        )
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("petro"))
                .andExpect(jsonPath("$.password").value("password"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllClients() throws Exception {
        mvc.perform(
                post("/user")
                        .with(httpBasic("john", "12345"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"username\": \"petro\"," +
                                "\"password\": \"password\"" +
                                "}")
        ).andExpect(status().isOk());

        mvc.perform(
                get("/user")
                        .with(httpBasic("john", "12345"))
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].username").value("petro"))
                .andExpect(jsonPath("$.[0].password").value("password"))
                .andExpect(status().isOk());
    }
}