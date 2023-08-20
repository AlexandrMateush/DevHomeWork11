package entity;

import org.example.TicketCrudService;
import org.example.entity.Client;
import org.example.entity.Planet;
import org.example.entity.Ticket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TicketCrudServiceTests {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private TicketCrudService ticketCrudService;

    @BeforeEach
    public void setup() {
        entityManagerFactory = Persistence.createEntityManagerFactory("persistence.xml");
        entityManager = entityManagerFactory.createEntityManager();
        ticketCrudService = new TicketCrudService(entityManager);
    }

    @Test
    public void testTicketCrudOperations() {
        Client client = new Client();
        client.setName("John");
        entityManager.persist(client);

        Planet startPlanet = new Planet();
        startPlanet.setId("EARTH7");
        startPlanet.setName("Earth");
        entityManager.persist(startPlanet);

        Planet endPlanet = new Planet();
        endPlanet.setId("MARS8");
        endPlanet.setName("Mars");
        entityManager.persist(endPlanet);

        Ticket ticket = new Ticket();
        ticket.setClient(client);
        ticket.setStartPlanet(startPlanet);
        ticket.setEndPlanet(endPlanet);
        ticketCrudService.addTicket(ticket);

        Ticket retrievedTicket = entityManager.find(Ticket.class, ticket.getId());
        assertEquals(client.getName(), retrievedTicket.getClient().getName());
        assertEquals(startPlanet.getName(), retrievedTicket.getStartPlanet().getName());
        assertEquals(endPlanet.getName(), retrievedTicket.getEndPlanet().getName());
    }

    @Test
    public void testGetAllTickets() {
        Client client1 = new Client();
        client1.setName("John");
        entityManager.persist(client1);

        Client client2 = new Client();
        client2.setName("Alice");
        entityManager.persist(client2);

        Planet planet1 = new Planet();
        planet1.setId("EARTH7");
        planet1.setName("Earth");
        entityManager.persist(planet1);

        Planet planet2 = new Planet();
        planet2.setId("MARS8");
        planet2.setName("Mars");
        entityManager.persist(planet2);

        Ticket ticket1 = new Ticket();
        ticket1.setClient(client1);
        ticket1.setStartPlanet(planet1);
        ticket1.setEndPlanet(planet2);
        ticketCrudService.addTicket(ticket1);

        Ticket ticket2 = new Ticket();
        ticket2.setClient(client2);
        ticket2.setStartPlanet(planet2);
        ticket2.setEndPlanet(planet1);
        ticketCrudService.addTicket(ticket2);

        List<Ticket> tickets = ticketCrudService.getAllTickets();

        assertEquals(2, tickets.size());

        Ticket retrievedTicket1 = entityManager.find(Ticket.class, ticket1.getId());
        Ticket retrievedTicket2 = entityManager.find(Ticket.class, ticket2.getId());

        assertEquals(retrievedTicket1.getClient().getName(), tickets.get(0).getClient().getName());
        assertEquals(retrievedTicket2.getClient().getName(), tickets.get(1).getClient().getName());
    }
}


