package org.example.CrudServices;

import org.example.entity.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

    public class TicketCrudService {

    @PersistenceContext
    private EntityManager entityManager;

    public TicketCrudService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public void createTicket(Ticket ticket) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(ticket);
        transaction.commit();
    }

    public Ticket getTicketById(Long id) {
        return entityManager.find(Ticket.class, id);
    }

    public void updateTicket(Ticket ticket) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(ticket);
        transaction.commit();
    }

    public List<Ticket> getAllTickets() {
        TypedQuery<Ticket> query = entityManager.createQuery("SELECT t FROM Ticket t", Ticket.class);
        return query.getResultList();
    }

    public void deleteTicket(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Ticket ticket = entityManager.find(Ticket.class, id);
        if (ticket != null) {
            entityManager.remove(ticket);
        }
        transaction.commit();
    }
        public Ticket addTicket(Ticket ticket) {
            entityManager.getTransaction().begin();
            entityManager.persist(ticket);
            entityManager.getTransaction().commit();
            return ticket;
        }
}
