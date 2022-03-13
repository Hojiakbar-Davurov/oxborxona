package com.example.appomborxona.controller;

import com.example.appomborxona.entity.Category;
import com.example.appomborxona.payload.CategoryDto;
import com.example.appomborxona.payload.Result;
import com.example.appomborxona.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping
    public Result add(@RequestBody CategoryDto categoryDto) {
        return categoryService.add(categoryDto);
    }

    @GetMapping
    public List<Category> getAll() {
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Category> get(@PathVariable Integer id) {
        return categoryService.get(id);
    }

    @PutMapping("/{id}")
    public Result edit(@RequestBody CategoryDto categoryDto, @PathVariable Integer id) {
        return categoryService.edit(categoryDto, id);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return categoryService.delete(id);
    }
}
