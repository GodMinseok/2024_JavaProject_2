package org.example;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A")
public class Album extends Item{

    private String artist;

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
