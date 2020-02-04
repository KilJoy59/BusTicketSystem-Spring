package com.services;

import com.entity.Driver;
import com.repositories.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class DriverService {

    private DriverRepository repository;
    private RequestValidator requestValidator;

    public Driver addDriver(Driver driver) {
        requestValidator.driverRequestValidator(driver);

        return repository.save(driver);
    }

    public List<Driver> findAll() {
        return repository.findAll();
    }

    public Driver findOne(Integer id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found"));
    }

    @Autowired
    public void setRepository(DriverRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setRequestValidator(RequestValidator requestValidator) {
        this.requestValidator = requestValidator;
    }
}
