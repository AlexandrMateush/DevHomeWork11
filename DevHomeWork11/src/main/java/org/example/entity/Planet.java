package org.example.entity;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "planet")
public class Planet {
    @Id
    private String id;

    private String name;
    @OneToMany(mappedBy = "fromPlanet")
    private Set<Ticket> departureTickets;

    @OneToMany(mappedBy = "toPlanet")
    private Set<Ticket> arrivalTickets;

    public Planet(String name) {
        this.name = name;
    }

    public Planet(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Planet() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

