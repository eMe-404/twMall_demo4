package com.tw.demo4.service;

import com.tw.demo4.entity.Product;
import com.tw.demo4.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAll(int pageNum, int pageSize, String sort) {
        if (sort.equals("all")) {
            return productRepository.findAll();
        }

        Sort orders = new Sort(sort.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, "Price");
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize, orders);
        return productRepository.findAll(pageRequest).getContent();
    }

    public Optional<Product> get(int id) {
        return productRepository.findById(id);
    }

    public Product add(Product product) {
        return productRepository.save(product);
    }

    public Product update(Product product, int id) {
        Optional<Product> toUpdateProduct = productRepository.findById(id);
        if (toUpdateProduct.isPresent()) {
            product.setId(id);
            return productRepository.save(product);
        }
        return null;
    }

    public boolean delete(int id) {
        Optional<Product> toDeletProduct = productRepository.findById(id);
        if (toDeletProduct.isPresent()) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
