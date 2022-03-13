package com.example.appomborxona.controller;

import com.example.appomborxona.entity.Output;
import com.example.appomborxona.payload.OutputDto;
import com.example.appomborxona.payload.Result;
import com.example.appomborxona.service.OutputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/output")
public class OutputController {
    @Autowired
    OutputService outputService;

    @PostMapping
    public Result add(@RequestBody OutputDto outputDto) {
        return outputService.add(outputDto);
    }

    @GetMapping
    public List<Output> getAll() {
        return outputService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Output> get(@PathVariable Integer id) {
        return outputService.get(id);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody OutputDto outputDto) {
        return outputService.edit(id, outputDto);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return outputService.delete(id);
    }
}