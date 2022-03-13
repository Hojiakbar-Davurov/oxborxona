package com.example.appomborxona.service;

import com.example.appomborxona.entity.Input;
import com.example.appomborxona.entity.InputProduct;
import com.example.appomborxona.entity.Product;
import com.example.appomborxona.payload.InputProductDto;
import com.example.appomborxona.payload.Result;
import com.example.appomborxona.repository.InputProductRepository;
import com.example.appomborxona.repository.InputRepository;
import com.example.appomborxona.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InputProductService {
    @Autowired
    InputProductRepository inputProductRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    InputRepository inputRepository;

    public Result add(InputProductDto inputProductDto) {

        // Exists product
        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (optionalProduct.isEmpty())
            return new Result("Product not found", false);

        // Exists product
        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (optionalInput.isEmpty())
            return new Result("Input not found", false);

        // Add inputProduct
        InputProduct inputProduct = new InputProduct();
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setExpireDate(inputProductDto.getExpireDate());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setInput(optionalInput.get());

        inputProductRepository.save(inputProduct);
        return new Result("Input product added", true);
    }

    public List<InputProduct> getAll() {
        return inputProductRepository.findAll();
    }

    public Optional<InputProduct> get(Integer id) {
        return inputProductRepository.findById(id);
    }

    public Result edit(Integer id, InputProductDto inputProductDto) {

        //Find inputProduct by id
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (optionalInputProduct.isEmpty())
            return new Result("InputProduct not found", false);

        // Exists product
        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (optionalProduct.isEmpty())
            return new Result("Product not found", false);

        // Exists product
        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (optionalInput.isEmpty())
            return new Result("Input not found", false);

        // Edit inputProduct
        InputProduct inputProduct = optionalInputProduct.get();
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setExpireDate(inputProductDto.getExpireDate());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setInput(optionalInput.get());

        inputProductRepository.save(inputProduct);
        return new Result("Input product edited", true);

    }

    public Result delete(Integer id) {
        inputProductRepository.deleteById(id);
        return new Result("InputProduct deleted", true);
    }
}
