package com.example.appomborxona.service;

import com.example.appomborxona.entity.Currency;
import com.example.appomborxona.payload.Result;
import com.example.appomborxona.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {
    @Autowired
    CurrencyRepository currencyRepository;

    public Result add(Currency currency) {

        // Exists Currency
        boolean exists = currencyRepository.existsByName(currency.getName());
        if (exists)
            return new Result("Currency already exists", false);

        // Add Currency
        currencyRepository.save(currency);
        return new Result("Currency added", true);
    }

    public List<Currency> getAll() {
        return currencyRepository.findAll();
    }

    public Optional<Currency> get(Integer id) {
        return currencyRepository.findById(id);
    }

    public Result edit(Integer id, Currency currency) {

        // Find Currency
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (optionalCurrency.isEmpty())
            return new Result("Currency not found", false);

        // Edit Currency
        Currency editingCurrency = optionalCurrency.get();
        editingCurrency.setName(currency.getName());
        currencyRepository.save(editingCurrency);
        return new Result("Currency edited", true);
    }

    public Result delete(Integer id) {
        currencyRepository.deleteById(id);
        return new Result("Currency deleted", true);
    }
}
