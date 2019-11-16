package de.oneaxis.apollon.connect.model.musician;

import de.oneaxis.ddd.conceptual.ValueObject;

@ValueObject
public class PlayedInstrument {
    public final String instrumentName;

    public PlayedInstrument(String instrumentName) {
        this.instrumentName = instrumentName;
    }
}
