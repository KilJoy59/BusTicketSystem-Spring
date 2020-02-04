package com.services;

import com.entity.Bus;
import com.entity.Driver;
import com.repositories.BusRepository;
import com.repositories.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class BusService {

    private BusRepository repository;
    private DriverRepository driverRepository;
    private RequestValidator requestValidator;

    public Bus addBus(Bus bus) {
        requestValidator.busRequestValidator(bus);

        return repository.save(bus);
    }

    public Bus changeDriverOnBus(Integer busId, Integer driverId) {

        requestValidator.busIdDriverIdValidator(busId, driverId);

        Optional<Bus> bus = repository.findById(busId);
        Optional<Driver> driver = driverRepository.findById(driverId);
        if (bus.isPresent() && driver.isPresent()) {
            bus.orElseThrow(() -> new EntityNotFoundException("Not found"))
                    .setDriver(driver.orElseThrow(() -> new EntityNotFoundException("Not found")));
        }
        return bus.orElseThrow(() -> new EntityNotFoundException("Not found"));
    }

    public Bus findOne(Integer id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found"));
    }

    public List<Bus> findAll() {
        return repository.findAll();
    }

    @Autowired
    public void setRepository(BusRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setDriverRepository(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Autowired
    public void setRequestValidator(RequestValidator requestValidator) {
        this.requestValidator = requestValidator;
    }
}
