package com.example.appomborxona.controller;

import com.example.appomborxona.entity.Input;
import com.example.appomborxona.payload.InputDto;
import com.example.appomborxona.payload.Result;
import com.example.appomborxona.service.InputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/input")
public class InputController {
    @Autowired
    InputService inputService;

    @PostMapping
    public Result add(@RequestBody InputDto inputDto) {
        return inputService.add(inputDto);
    }

    @GetMapping
    public List<Input> getAll() {
        return inputService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Input> get(@PathVariable Integer id) {
        return inputService.get(id);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody InputDto inputDto) {
        return inputService.edit(id, inputDto);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return inputService.delete(id);
    }
}