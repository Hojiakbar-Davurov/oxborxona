package com.example.appomborxona.service;

import com.example.appomborxona.entity.Warehouse;
import com.example.appomborxona.payload.Result;
import com.example.appomborxona.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {
    @Autowired
    WarehouseRepository warehouseRepository;

    public Result add(Warehouse warehouse) {

        // Exists Warehouse
        boolean exists = warehouseRepository.existsByName(warehouse.getName());
        if (exists)
            return new Result("Warehouse already exists", false);

        // Add Warehouse
        warehouseRepository.save(warehouse);
        return new Result("Warehouse added", true);
    }

    public List<Warehouse> getAll() {
        return warehouseRepository.findAll();
    }

    public Optional<Warehouse> get(Integer id) {
        return warehouseRepository.findById(id);
    }

    public Result edit(Integer id, Warehouse warehouse) {

        // Find Warehouse
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (optionalWarehouse.isEmpty())
            return new Result("Warehouse not found", false);

        // Edit Warehouse
        Warehouse editingWarehouse = optionalWarehouse.get();
        editingWarehouse.setName(warehouse.getName());
        warehouseRepository.save(editingWarehouse);
        return new Result("Warehouse edited", true);
    }

    public Result delete(Integer id) {
        warehouseRepository.deleteById(id);
        return new Result("Warehouse deleted", true);
    }
}
