package com.services;

import com.entity.Ticket;
import com.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TicketService {

    private TicketRepository repository;

    public Ticket findOne(Integer id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found"));
    }

    public List<Ticket> findAll() {
        return (List<Ticket>) repository.findAll();
    }

    @Autowired
    public void setRepository(TicketRepository repository) {
        this.repository = repository;
    }
}
