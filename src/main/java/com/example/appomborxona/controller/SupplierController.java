package com.example.appomborxona.controller;

import com.example.appomborxona.entity.Supplier;
import com.example.appomborxona.payload.Result;
import com.example.appomborxona.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    SupplierService supplierService;

    @PostMapping
    public Result add(@RequestBody Supplier supplier) {
        return supplierService.add(supplier);
    }

    @GetMapping
    public List<Supplier> getAll() {
        return supplierService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Supplier> get(@PathVariable Integer id) {
        return supplierService.get(id);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody Supplier supplier) {
        return supplierService.edit(id, supplier);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return supplierService.delete(id);
    }
}
