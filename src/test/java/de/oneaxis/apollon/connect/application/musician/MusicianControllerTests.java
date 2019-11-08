package de.oneaxis.apollon.connect.application.musician;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MusicianControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int randomServerPort;

    @Test
    void ShouldCreateBlankMusician() {
        String url = String.format("http://localhost:%s/musicians", randomServerPort);
        MusicianResponse musician = restTemplate.postForObject(url, null, MusicianResponse.class);
        assertThat(musician).isNotNull();
        assertThat(musician.id).isNotNull();
    }
}
