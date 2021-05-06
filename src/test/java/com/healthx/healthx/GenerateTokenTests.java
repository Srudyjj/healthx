package com.healthx.healthx;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GenerateTokenTests {

    @Autowired
    private MockMvc mvc;

    @Test
    void generateTokenValidUserAndClientTest() throws Exception {

        mvc.perform(
                post("/oauth/token")
                        .with(httpBasic("client", "secret"))
                        .queryParam("grant_type", "password")
                        .queryParam("username", "john")
                        .queryParam("password", "12345")
                        .queryParam("scope", "read")
        )
                .andExpect(jsonPath("$.access_token").exists())
                .andExpect(status().isOk());
    }

    @Test
    void generateTokenInvalidClientTest() throws Exception {
        mvc.perform(
                post("/oauth/token")
                        .with(httpBasic("wrong_client", "secret"))
                        .queryParam("grant_type", "password")
                        .queryParam("username", "john")
                        .queryParam("password", "12345")
                        .queryParam("scope", "read")
        )
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.access_token").doesNotExist());
    }

    @Test
    void generateTokenInvalidUserTest() throws Exception {
        mvc.perform(
                post("/oauth/token")
                        .with(httpBasic("client", "secret"))
                        .queryParam("grant_type", "password")
                        .queryParam("username", "wrong")
                        .queryParam("password", "12345")
                        .queryParam("scope", "read")
        )
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.access_token").doesNotExist());
    }

    @Test
    void generateTokenPasswordNotValidTest() throws Exception {
        mvc.perform(
                post("/oauth/token")
                        .with(httpBasic("client", "other_secret"))
                        .queryParam("grant_type", "password")
                        .queryParam("username", "john")
                        .queryParam("password", "wrong_password")
                        .queryParam("scope", "read")
        )
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.access_token").doesNotExist());
    }

    @Test
    void generateRefreshTokenTest() throws Exception {
        mvc.perform(
                post("/oauth/token")
                        .with(httpBasic("client", "secret"))
                        .queryParam("grant_type", "password")
                        .queryParam("username", "john")
                        .queryParam("password", "12345")
                        .queryParam("scope", "read")
        )
                .andExpect(jsonPath("$.access_token").exists())
                .andExpect(jsonPath("$.refresh_token").exists())
                .andExpect(status().isOk());
    }
}
