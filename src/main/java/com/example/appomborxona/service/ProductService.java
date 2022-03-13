package com.example.appomborxona.service;

import com.example.appomborxona.entity.Attachment;
import com.example.appomborxona.entity.Category;
import com.example.appomborxona.entity.Measurement;
import com.example.appomborxona.entity.Product;
import com.example.appomborxona.payload.ProductDto;
import com.example.appomborxona.payload.Result;
import com.example.appomborxona.repository.AttachmentRepository;
import com.example.appomborxona.repository.CategoryReposiroty;
import com.example.appomborxona.repository.MeasurementRepository;
import com.example.appomborxona.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryReposiroty categoryReposiroty;

    @Autowired
    MeasurementRepository measurementRepository;

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    AttachmentService attachmentService;

    public Result add(ProductDto productDto) {

        //Check Measurement exists
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (optionalMeasurement.isEmpty())
            return new Result("Measurement not found", false);

        //Check Category exists
        Optional<Category> optionalCategory = categoryReposiroty.findById(productDto.getCategoryId());
        if (optionalCategory.isEmpty())
            return new Result("Category not found", false);

        //Check Attachment exists
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (optionalAttachment.isEmpty())
            return new Result("Attachment not found", false);

        //Add Product
        Product product = new Product();
        product.setName(productDto.getName());
        product.setCategory(optionalCategory.get());
        product.setMeasurement(optionalMeasurement.get());
        product.setPhoto(optionalAttachment.get());
        productRepository.save(product);
        return new Result("Product added", true);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Optional<Product> get(Integer id) {
        return productRepository.findById(id);
    }

    public Result edit(Integer id, ProductDto productDto) {

        //Check Measurement exists
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty())
            return new Result("Product not found", false);

        //Check Measurement exists
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (optionalMeasurement.isEmpty())
            return new Result("Measurement not found", false);

        //Check Category exists
        Optional<Category> optionalCategory = categoryReposiroty.findById(productDto.getCategoryId());
        if (optionalCategory.isEmpty())
            return new Result("Category not found", false);

        //Check Attachment exists
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (optionalAttachment.isEmpty())
            return new Result("Attachment not found", false);

        //Add Product
        Product product = optionalProduct.get();
        product.setName(productDto.getName());
        product.setMeasurement(optionalMeasurement.get());
        product.setCategory(optionalCategory.get());
        product.setPhoto(optionalAttachment.get());
        productRepository.save(product);
        return new Result("Product edited", true);
    }

    public Result delete(Integer id) {
        //Check Product exists
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty())
            return new Result("Product not found",false);

        //Delete Product
        productRepository.deleteById(id);

        //Delete File belong to Product
        attachmentService.delete(optionalProduct.get().getId());
        return new Result("Product deleted", true);
    }
}
