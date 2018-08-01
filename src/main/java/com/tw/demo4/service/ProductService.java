package com.tw.demo4.service;

import com.tw.demo4.entity.Product;
import com.tw.demo4.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Optional<Product> get(int id) {
        return productRepository.findById(id);
    }
}
