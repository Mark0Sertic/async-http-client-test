package com.example.demo.controller;

import com.example.demo.TestBase;
import com.example.demo.service.*;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class TestControllerTest extends TestBase {

    private static final String BASE_URL = "/test";

    /*@Autowired
    private  FetchServiceStandAlone fetchServiceImpl;

    @MockBean
    private FetchService fetchService;*/


    @Test
    void timeoutHappens() {
        //given
        givenPostResponse();
        givenGetResponse();

        /*given(fetchService.get(any())).willAnswer(
                invocationOnMock -> fetchServiceImpl.get(invocationOnMock.getArgument(0)).block());*/

        //when
        var actual = RestAssured.with()
                                .contentType(ContentType.JSON)
                                .body("{}")
                                .post(BASE_URL);

        //then
        then(actual.getStatusCode()).isEqualTo(HttpStatus.OK.value());

    }

    private void givenPostResponse() {
        WireMock.givenThat(post(urlPathEqualTo("/example"))
                                   .willReturn(aResponse().withStatus(200)
                                                          .withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                                                          .withBody("{ˇ\"id\": 1}")));
    }

    private void givenGetResponse() {
        WireMock.givenThat(get(urlPathEqualTo("/example"))
                                   .willReturn(aResponse().withStatus(200)
                                                          .withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                                                          .withBody("{ˇ\"id\": 2}")));
    }
}
