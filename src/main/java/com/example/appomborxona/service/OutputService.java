package com.example.appomborxona.service;

import com.example.appomborxona.entity.Client;
import com.example.appomborxona.entity.Currency;
import com.example.appomborxona.entity.Output;
import com.example.appomborxona.entity.Warehouse;
import com.example.appomborxona.payload.OutputDto;
import com.example.appomborxona.payload.Result;
import com.example.appomborxona.repository.ClientRepository;
import com.example.appomborxona.repository.CurrencyRepository;
import com.example.appomborxona.repository.OutputRepository;
import com.example.appomborxona.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OutputService {
    @Autowired
    OutputRepository outputRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    ClientRepository clientRepository;

    public Result add(OutputDto outputDto) {

        // Exists Currency
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrency_id());
        if (optionalCurrency.isEmpty())
            return new Result("Currency not found", false);

        // Exists Warehouse
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouse_id());
        if (optionalWarehouse.isEmpty())
            return new Result("Warehouse not found", false);

        // Exists Client
        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClient_id());
        if (optionalClient.isEmpty())
            return new Result("Client not found", false);

        // Add Output
        Output output = new Output();
        output.setFactureNumber(outputDto.getFactureNumber());
        output.setCurrency(optionalCurrency.get());
        output.setClient(optionalClient.get());
        output.setWarehouse(optionalWarehouse.get());
        outputRepository.save(output);
        return new Result("Output added", true);
    }


    public List<Output> getAll() {
        return outputRepository.findAll();
    }

    public Optional<Output> get(Integer id) {
        return outputRepository.findById(id);
    }

    public Result edit(Integer id, OutputDto outputDto) {

        // Find output by id
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (optionalOutput.isEmpty())
            return new Result("Output not found", false);

        // Exists Currency
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrency_id());
        if (optionalCurrency.isEmpty())
            return new Result("Currency not found", false);

        // Exists Warehouse
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouse_id());
        if (optionalWarehouse.isEmpty())
            return new Result("Warehouse not found", false);

        // Exists Client
        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClient_id());
        if (optionalClient.isEmpty())
            return new Result("Client not found", false);

        // Edit Output
        Output output = optionalOutput.get();
        output.setFactureNumber(outputDto.getFactureNumber());
        output.setCurrency(optionalCurrency.get());
        output.setClient(optionalClient.get());
        output.setWarehouse(optionalWarehouse.get());
        outputRepository.save(output);
        return new Result("Output edited", true);

    }

    public Result delete(Integer id) {
        outputRepository.deleteById(id);
        return new Result("Output deleted", true);
    }
}
