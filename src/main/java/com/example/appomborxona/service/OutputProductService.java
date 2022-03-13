package com.example.appomborxona.service;

import com.example.appomborxona.entity.Output;
import com.example.appomborxona.entity.OutputProduct;
import com.example.appomborxona.entity.Product;
import com.example.appomborxona.payload.OutputProductDto;
import com.example.appomborxona.payload.Result;
import com.example.appomborxona.repository.OutputProductRepository;
import com.example.appomborxona.repository.OutputRepository;
import com.example.appomborxona.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OutputProductService {
    @Autowired
    OutputProductRepository outputProductRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OutputRepository outputRepository;

    public Result add(OutputProductDto outputProductDto) {

        // Exists product
        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if (optionalProduct.isEmpty())
            return new Result("Product not found", false);

        // Exists output
        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if (optionalOutput.isEmpty())
            return new Result("Output not found", false);

        // Add OutputProduct
        OutputProduct outputProduct = new OutputProduct();
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setOutput(optionalOutput.get());
        outputProductRepository.save(outputProduct);
        return new Result("Output product added", true);
    }

    public List<OutputProduct> getAll() {
        return outputProductRepository.findAll();
    }

    public Optional<OutputProduct> get(Integer id) {
        return outputProductRepository.findById(id);
    }

    public Result edit(Integer id, OutputProductDto outputProductDto) {

        // Find outputProduct by id
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (optionalOutputProduct.isEmpty())
            return new Result("Output product not found", false);

        // Exists product
        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if (optionalProduct.isEmpty())
            return new Result("Product not found", false);

        // Exists output
        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if (optionalOutput.isEmpty())
            return new Result("Output not found", false);

        // Add OutputProduct
        OutputProduct outputProduct = optionalOutputProduct.get();
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setOutput(optionalOutput.get());

        outputProductRepository.save(outputProduct);
        return new Result("Output product edited", true);
    }

    public Result delete(Integer id) {
        outputProductRepository.deleteById(id);
        return new Result("OutputProduct deleted", true);
    }
}
