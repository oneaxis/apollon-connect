package de.oneaxis.apollon.connect.application;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract public class AbstractControllerTest {

    @LocalServerPort
    protected int randomServerPort;
    protected ApollonConnectAPIClient apiClient;
}
