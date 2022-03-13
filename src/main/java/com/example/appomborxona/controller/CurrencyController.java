package com.example.appomborxona.controller;

import com.example.appomborxona.entity.Currency;
import com.example.appomborxona.payload.Result;
import com.example.appomborxona.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/currency")
public class CurrencyController {
    @Autowired
    CurrencyService currencyService;

    @PostMapping
    public Result add(@RequestBody Currency currency) {
        return currencyService.add(currency);
    }

    @GetMapping
    public List<Currency> getAll() {
        return currencyService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Currency> get(@PathVariable Integer id) {
        return currencyService.get(id);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody Currency currency) {
        return currencyService.edit(id, currency);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return currencyService.delete(id);
    }
}