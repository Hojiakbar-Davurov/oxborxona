package com.example.appomborxona.service;

import com.example.appomborxona.entity.Supplier;
import com.example.appomborxona.payload.Result;
import com.example.appomborxona.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    SupplierRepository supplierRepository;

    public Result add(Supplier supplier) {

        // Exists Supplier
        boolean exists = supplierRepository.existsByPhoneNumber(supplier.getPhoneNumber());
        if (exists)
            return new Result("Phone number already exists", false);

        // Add Supplier
        supplierRepository.save(supplier);
        return new Result("Supplier added", true);
    }

    public List<Supplier> getAll() {
        return supplierRepository.findAll();
    }

    public Optional<Supplier> get(Integer id) {
        return supplierRepository.findById(id);
    }

    public Result edit(Integer id, Supplier supplier) {

        // Find Supplier
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isEmpty())
            return new Result("Supplier not found", false);

        //Edit supplier
        Supplier editingSupplier = optionalSupplier.get();
        editingSupplier.setName(supplier.getName());
        editingSupplier.setPhoneNumber(supplier.getPhoneNumber());
        supplierRepository.save(editingSupplier);
        return new Result("Supplier edited", true);
    }

    public Result delete(Integer id) {
        supplierRepository.deleteById(id);
        return new Result("Supplier deleted", true);
    }
}
