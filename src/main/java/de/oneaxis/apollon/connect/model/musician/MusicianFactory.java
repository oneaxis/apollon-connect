package de.oneaxis.apollon.connect.model.musician;

import org.bson.types.ObjectId;

import java.util.HashSet;

public class MusicianFactory {

    public static Musician createBlank() {
        MusicianId id = new MusicianId(new ObjectId());
        return new Musician(id, new HashSet<>(), new HashSet<>(), new HashSet<>());
    }
}
