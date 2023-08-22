package entity;

import org.example.CrudServices.TicketCrudService;
import org.example.entity.Client;
import org.example.entity.Planet;
import org.example.entity.Ticket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


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
        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.getTransaction().commit();

        Planet startPlanet = new Planet();
        startPlanet.setId("EARTH7");
        startPlanet.setName("Earth");
        entityManager.getTransaction().begin();
        entityManager.persist(startPlanet);
        entityManager.getTransaction().commit();

        Planet endPlanet = new Planet();
        endPlanet.setId("MARS8");
        endPlanet.setName("Mars");
        entityManager.getTransaction().begin();
        entityManager.persist(endPlanet);
        entityManager.getTransaction().commit();

        Ticket ticket = new Ticket();
        ticket.setClient(client);
        ticket.setStartPlanet(startPlanet);
        ticket.setEndPlanet(endPlanet);
        ticketCrudService.addTicket(ticket);

        Ticket retrievedTicket = entityManager.find(Ticket.class, ticket.getId());
        assertEquals(client.getName(), retrievedTicket.getClient().getName());
        assertEquals(startPlanet.getName(), retrievedTicket.getStartPlanet().getName());
        assertEquals(endPlanet.getName(), retrievedTicket.getEndPlanet().getName());

        entityManager.close();
    }


}