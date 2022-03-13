package com.example.appomborxona.controller;

import com.example.appomborxona.entity.OutputProduct;
import com.example.appomborxona.payload.OutputProductDto;
import com.example.appomborxona.payload.Result;
import com.example.appomborxona.service.OutputProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/outputProduct")
public class OutputProductController {
    @Autowired
    OutputProductService service;

    @PostMapping
    public Result add(@RequestBody OutputProductDto outputProductDto) {
        return service.add(outputProductDto);
    }

    @GetMapping
    public List<OutputProduct> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<OutputProduct> get(@PathVariable Integer id) {
        return service.get(id);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody OutputProductDto outputProductDto) {
        return service.edit(id, outputProductDto);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return service.delete(id);
    }
}