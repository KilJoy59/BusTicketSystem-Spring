package com.controllers;

import com.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/busStation")
public class TicketController {

    private TicketService service;

    /**
     * Этот метод по {id} извлекает конкретный 'Билет'
     *
     * response{
     * "id": ... - ID запрашиваемой записи
     * "place": ...- место билета запрашиваемой записи
     * "price": ... -  цена билета запрашиваемой записи
     * "isPaid": ... - true(если билет оплачен) или false(если билет не оплачен) запрашиваемой записи
     * }
     */

    @RequestMapping(value = "/tickets/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> findOneTicket(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    /**
     * Этот метод извлекает список всех 'Билетов'
     *
     * response[{...}]
     *
     */

    @RequestMapping(value = "/tickets", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> findAllTickets() {
        return ResponseEntity.ok(service.findAll());
    }

    @Autowired
    public void setService(TicketService service) {
        this.service = service;
    }
}
