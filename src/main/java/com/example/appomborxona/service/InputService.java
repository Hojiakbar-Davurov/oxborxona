package com.example.appomborxona.service;

import com.example.appomborxona.entity.Currency;
import com.example.appomborxona.entity.Input;
import com.example.appomborxona.entity.Supplier;
import com.example.appomborxona.entity.Warehouse;
import com.example.appomborxona.payload.InputDto;
import com.example.appomborxona.payload.Result;
import com.example.appomborxona.repository.CurrencyRepository;
import com.example.appomborxona.repository.InputRepository;
import com.example.appomborxona.repository.SupplierRepository;
import com.example.appomborxona.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InputService {
    @Autowired
    InputRepository inputRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    SupplierRepository supplierRepository;

    public Result add(InputDto inputDto) {

        // Exists Currency
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrency_id());
        if (optionalCurrency.isEmpty())
            return new Result("Currency not found", false);

        // Exists Warehouse
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouse_id());
        if (optionalWarehouse.isEmpty())
            return new Result("Warehouse not found", false);

        // Exists Supplier
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSuppler_id());
        if (optionalSupplier.isEmpty())
            return new Result("Supplier not found", false);

        //Add input
        Input input = new Input();
        input.setFactureNumber(inputDto.getFactureNumber());
        input.setCurrency(optionalCurrency.get());
        input.setSupplier(optionalSupplier.get());
        input.setWarehouse(optionalWarehouse.get());
        inputRepository.save(input);
        return new Result("Input added", true);
    }

    public List<Input> getAll() {
        return inputRepository.findAll();
    }

    public Optional<Input> get(Integer id) {
        return inputRepository.findById(id);
    }

    public Result edit(Integer id, InputDto inputDto) {

        // Find Currency by id
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (optionalInput.isEmpty())
            return new Result("Input not found", false);

        // Exists Currency
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrency_id());
        if (optionalCurrency.isEmpty())
            return new Result("Currency not found", false);

        // Exists Warehouse
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouse_id());
        if (optionalWarehouse.isEmpty())
            return new Result("Warehouse not found", false);

        // Exists Supplier
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSuppler_id());
        if (optionalSupplier.isEmpty())
            return new Result("Supplier not found", false);

        //Edit input
        Input input = optionalInput.get();
        input.setFactureNumber(inputDto.getFactureNumber());
        input.setCurrency(optionalCurrency.get());
        input.setSupplier(optionalSupplier.get());
        input.setWarehouse(optionalWarehouse.get());
        inputRepository.save(input);
        return new Result("Input edited", true);
    }

    public Result delete(Integer id) {
        inputRepository.deleteById(id);
        return new Result("Input deleted", true);
    }
}
