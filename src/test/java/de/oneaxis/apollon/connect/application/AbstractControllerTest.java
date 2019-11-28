package de.oneaxis.apollon.connect.application;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract public class AbstractControllerTest {

    @LocalServerPort
    protected int randomServerPort;
    protected ApollonConnectAPIClient apiClient;

    @BeforeEach
    void init() {
        apiClient = new ApollonConnectAPIClient(randomServerPort);
    }
}
