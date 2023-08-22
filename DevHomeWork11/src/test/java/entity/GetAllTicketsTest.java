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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetAllTicketsTest {
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
    public void testGetAllTickets() {
        Client client1 = new Client();
        client1.setName("John");
        entityManager.getTransaction().begin();
        entityManager.persist(client1);
        entityManager.getTransaction().commit();

        Client client2 = new Client();
        client2.setName("Alice");
        entityManager.getTransaction().begin();
        entityManager.persist(client2);
        entityManager.getTransaction().commit();

        Planet planet1 = new Planet();
        planet1.setId("EARTH7");
        planet1.setName("Earth");
        entityManager.getTransaction().begin();
        entityManager.persist(planet1);
        entityManager.getTransaction().commit();

        Planet planet2 = new Planet();
        planet2.setId("MARS8");
        planet2.setName("Mars");
        entityManager.getTransaction().begin();
        entityManager.persist(planet2);
        entityManager.getTransaction().commit();

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

        entityManager.close();
    }
}
