package de.oneaxis.apollon.connect.application.band;


import de.oneaxis.apollon.connect.application.AbstractControllerTest;
import de.oneaxis.apollon.connect.model.musician.Musician;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class BandControllerTests extends AbstractControllerTest {

    @Test
    void GivenExistingMusician_ShouldCreateNewBand() {
        Musician musician = apiClient.getNewMusician();

        //Create band
    }
}
