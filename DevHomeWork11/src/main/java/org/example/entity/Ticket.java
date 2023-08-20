package org.example.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "start_planet_id")
    private Planet startPlanet;

    @ManyToOne
    @JoinColumn(name = "end_planet_id")
    private Planet endPlanet;

    public Ticket() {
    }

    public Ticket(Long id, Client client, Planet startPlanet, Planet endPlanet) {
        this.id = id;
        this.client = client;
        this.startPlanet = startPlanet;
        this.endPlanet = endPlanet;
    }

    public Ticket(Client client, Planet startPlanet, Planet endPlanet) {
        this.client = client;
        this.startPlanet = startPlanet;
        this.endPlanet = endPlanet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Planet getStartPlanet() {
        return startPlanet;
    }

    public void setStartPlanet(Planet startPlanet) {
        this.startPlanet = startPlanet;
    }

    public Planet getEndPlanet() {
        return endPlanet;
    }

    public void setEndPlanet(Planet endPlanet) {
        this.endPlanet = endPlanet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}