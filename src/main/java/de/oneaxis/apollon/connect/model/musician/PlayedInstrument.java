package de.oneaxis.apollon.connect.model.musician;

import de.oneaxis.ddd.conceptual.ValueObject;

@ValueObject
class PlayedInstrument {
    final String instrumentName;

    PlayedInstrument(String instrumentName) {
        this.instrumentName = instrumentName;
    }
}
