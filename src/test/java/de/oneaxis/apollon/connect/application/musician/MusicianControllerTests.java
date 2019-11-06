package de.oneaxis.apollon.connect.application.musician;

import de.oneaxis.apollon.connect.model.musician.Musician;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MusicianControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int randomServerPort;

    @Test
    void ShouldCreateBlankMusician() {
        String url = String.format("http://localhost:%s/musicians", randomServerPort);
        Musician musician = restTemplate.postForObject(url, null, Musician.class);
        assertThat(musician).isNotNull();
        assertThat(musician.id).isNotNull();
    }
}
