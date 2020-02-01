package com.controllers;

import com.entity.Driver;
import com.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/busStation")
public class DriverController {

    private DriverService service;

    /**
     * Этот метод создает запись 'Водитель' в БД и возвращает эту запись с присвоенным ID.
     *
     * request{
     * "license":"..." - номер водилельского удостоверения (String)
     * "name":"..." - имя водителя (String)
     * "surname":"..." - фамилия водителя (String)
     * }
     *
     * response{
     * "id": ... - ID созданной записи
     * "license":"..." - номер водилельского удостоверения созданной записи
     * "name":"..." - имя водителя созданной записи
     * "surname":"..." - фамилия водителя созданной записи
     * }
     */

    @RequestMapping(value = "/drivers", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> addDriver(@RequestBody Driver driver) {
        return ResponseEntity.ok(service.addDriver(driver));
    }

    /**
     * Этот метод по {id} извлекает конкретного 'Водителя'
     *
     * response{
     * "id": ... - ID запрашиваемой записи
     * "license":"..." - номер водилельского удостоверения запрашиваемой записи
     * "name":"..." - имя водителя запрашиваемой записи
     * "surname":"..." - фамилия водителя запрашиваемой записи
     * }
     */

    @RequestMapping(value = "/drivers/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> findOneDriver(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    /**
     * Этот метод извлекает список всех 'Водителей'
     *
     * response[{...}]
     */

    @RequestMapping(value = "/drivers", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> findAllDrivers() {
        return ResponseEntity.ok(service.findAll());
    }

    @Autowired
    public void setService(DriverService service) {
        this.service = service;
    }
}
