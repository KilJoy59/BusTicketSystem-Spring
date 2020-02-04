package com.services;

import com.entity.Bus;
import com.entity.Driver;
import com.entity.Ticket;
import com.entity.Voyage;
import com.repositories.BusRepository;
import com.repositories.TicketRepository;
import com.repositories.VoyageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class VoyageService {

    private VoyageRepository repository;
    private TicketRepository ticketRepository;
    private BusRepository busRepository;
    private RequestValidator requestValidator;

    public Voyage addVoyage(Voyage voyage) {
        requestValidator.voyageRequestValidator(voyage);

        return repository.save(voyage);
    }

    public Voyage changeBusOnVoyage(Integer voyageId, Integer busId) {
        requestValidator.voyageIdBusIdValidator(voyageId, busId);

        Voyage voyage = repository.findById(voyageId).orElseThrow(() -> new EntityNotFoundException("Not found"));

        Bus bus = busRepository.findById(busId).orElseThrow(() -> new EntityNotFoundException("Not found"));

        if (voyage.getBus() != null && voyage.getBus().getDriver() != null) {
            Driver driver = voyage.getBus().getDriver();
            voyage.getBus().setDriver(null);

            repository.save(voyage);

            bus.setDriver(driver);
        }

        voyage.setBus(bus);

        return repository.save(voyage);
    }

    public Voyage addTicketsOnVoyage(Integer voyageId, Set<Ticket> tickets) {
        requestValidator.voyageIdSetOfTicketsValidator(voyageId, tickets);

        Voyage dbVoyage = repository.findById(voyageId).orElseThrow(() -> new EntityNotFoundException("Not found"));

        Set<Ticket> dbTickets = new HashSet<>(ticketRepository.findAll());
        for (Ticket ticket : dbTickets) {
            ticket.setVoyage(dbVoyage);
        }
        if (dbVoyage.getTickets() != null) {
            dbVoyage.getTickets().addAll(tickets);
        } else {
            dbVoyage.setTickets(tickets);
        }

        ticketRepository.saveAll(dbTickets);
        return repository.save(dbVoyage);
    }

    public String sellTicket(Integer voyageId, Integer ticketId) {
        requestValidator.voyageIdTicketIdValidator(voyageId, ticketId);

        ticketRepository.findById(ticketId).orElseThrow(() -> new EntityNotFoundException("Not found")).setPaid(true);

        return customResponse(voyageId, ticketId);
    }

    private String customResponse(Integer voyageId, Integer ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new EntityNotFoundException("Not found"));
        Voyage voyage = repository.findById(voyageId).orElseThrow(() -> new EntityNotFoundException("Not found"));

        String lineSeparator = System.lineSeparator();

        return "Voyage{".concat(lineSeparator)
                .concat("   number = ").concat(voyage.getNumber()).concat(lineSeparator)
                .concat("   busNumber = ").concat(voyage.getBus() != null ? voyage.getBus().getNumber() : "null").concat(lineSeparator)
                .concat("   busModel = ").concat(voyage.getBus() != null ? voyage.getBus().getModel() : "null").concat(lineSeparator)
                .concat("   driverName = ")
                .concat(voyage.getBus() != null && voyage.getBus().getDriver() != null ? voyage.getBus().getDriver().getName() : "null").concat(lineSeparator)
                .concat("   driverSurname = ")
                .concat(voyage.getBus() != null && voyage.getBus().getDriver() != null ? voyage.getBus().getDriver().getSurname() : "null").concat(lineSeparator)
                .concat("   ticketPrice = ").concat(ticket.getPrice().toString()).concat(lineSeparator)
                .concat("   ticketPlace = ").concat(ticket.getPlace().toString()).concat(lineSeparator)
                .concat("   isPaid = ").concat(String.valueOf(ticket.isPaid()))
                .concat("}");
    }

    public Voyage findOne(Integer id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found"));
    }


    public List<Voyage> findAll() {
        return (List<Voyage>) repository.findAll();
    }

    @Autowired
    public void setRepository(VoyageRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setTicketRepository(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Autowired
    public void setBusRepository(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    @Autowired
    public void setRequestValidator(RequestValidator requestValidator) {
        this.requestValidator = requestValidator;
    }
}
