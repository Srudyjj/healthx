package com.example.liveproject;

import com.example.gateway.GatewayApplication;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.example.liveproject.model.HealthAdvice;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockJwt;

@SpringBootTest(classes = GatewayApplication.class)
@AutoConfigureWebTestClient
public class GatewayRedirectToAdviceTests {

  @Autowired
  private WebTestClient client;

  private static WireMockServer wireMockServer;

  @BeforeAll
  static void init() {
    wireMockServer = new WireMockServer(new WireMockConfiguration().port(7070));
    wireMockServer.start();
    WireMock.configureFor("localhost", 7070);
  }

  @Test
  public void redirectToAdvicePostTest() {
    HealthAdvice advice = new HealthAdvice();
    advice.setUsername("bill");
    advice.setAdvice("advice");

    stubFor(WireMock.post(urlMatching("/advice"))
            .willReturn(aResponse()
                    .withStatus(OK.value())));

    client.mutateWith(mockJwt())
            .mutateWith(csrf())
            .post()
            .uri("/advice")
            .bodyValue(List.of(advice))
            .exchange()
            .expectStatus().isOk();

  }

  @Test
  public void redirectToAdvicePostNoAuthTest() {
    HealthAdvice advice = new HealthAdvice();
    advice.setUsername("bill");
    advice.setAdvice("advice");

    client.post()
            .uri("/advice")
            .bodyValue(List.of(advice))
            .exchange()
            .expectStatus().isForbidden();

  }



  @AfterAll
  static void tearDown() {
    wireMockServer.stop();
  }

}
