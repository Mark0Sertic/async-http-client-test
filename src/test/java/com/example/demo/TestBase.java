package com.example.demo;

import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.*;
import org.springframework.util.SocketUtils;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@TestInstance(PER_CLASS)
@SpringBootTest(classes = Main.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public abstract class TestBase {

    protected static final Integer WIREMOCK_PORT = SocketUtils.findAvailableTcpPort();

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setupClock() {
        System.setProperty("reactor.netty.ioWorkerCount", "100");
    }

    @BeforeEach
    void setUpRestAssured() {
        RestAssured.port = port;
    }

    @AfterEach
    void resetMocks() {
        WireMock.reset();
    }

    @DynamicPropertySource
    static void wireMockProperties(DynamicPropertyRegistry registry) {
        registry.add("wiremock.port", () -> WIREMOCK_PORT);
    }
}
