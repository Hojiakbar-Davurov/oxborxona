package com.example.appomborxona.controller;

import com.example.appomborxona.entity.Measurement;
import com.example.appomborxona.payload.Result;
import com.example.appomborxona.repository.MeasurementRepository;
import com.example.appomborxona.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {

    @Autowired
    MeasurementService measurementService;

    @Autowired
    MeasurementRepository measurementRepository;

    @PostMapping
    public Result add(@RequestBody Measurement measurement) {
        return measurementService.addService(measurement);
    }

    @GetMapping
    public List<Measurement> getAll() {
        return measurementService.getAllService();
    }

    @GetMapping("/{id}")
    public Optional<Measurement> get(@PathVariable Integer id) {
        return measurementService.getService(id);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody Measurement measurement) {
        return measurementService.editService(id, measurement);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return measurementService.delete(id);
    }
}
