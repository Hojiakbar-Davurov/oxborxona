package com.example.appomborxona.controller;

import com.example.appomborxona.entity.Product;
import com.example.appomborxona.payload.ProductDto;
import com.example.appomborxona.payload.Result;
import com.example.appomborxona.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping
    public Result add(@RequestBody ProductDto dto) {
        return productService.add(dto);
    }

    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Product> get(@PathVariable Integer id) {
        return productService.get(id);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody ProductDto dto) {
        return productService.edit(id, dto);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return productService.delete(id);
    }

}
