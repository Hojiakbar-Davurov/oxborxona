package com.example.appomborxona.service;

import com.example.appomborxona.entity.Measurement;
import com.example.appomborxona.payload.Result;
import com.example.appomborxona.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {

    @Autowired
    MeasurementRepository measurementRepository;

    public Result addService(Measurement measurement) {

        // Check measurement exists
        boolean existsByName = measurementRepository.existsByName(measurement.getName());
        if (existsByName)
            return new Result("This measurement already exists", false);

        // Add measurement
        measurementRepository.save(measurement);
        return new Result("Measurement added", true);
    }

    public List<Measurement> getAllService() {
        return measurementRepository.findAll();
    }

    public Optional<Measurement> getService(Integer id) {
        return measurementRepository.findById(id);
    }

    public Result editService(Integer id, Measurement measurement) {

        // Check measurement exists
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (optionalMeasurement.isEmpty())
            return new Result("Measurement not found", false);

        // Edit measurement
        Measurement editingMeasurement = optionalMeasurement.get();
        editingMeasurement.setName(measurement.getName());
        measurementRepository.save(editingMeasurement);
        return new Result("Measurement edited", true);
    }

    public Result delete(Integer id) {
        measurementRepository.deleteById(id);
        return new Result("Measurement deleted", true);
    }
}
