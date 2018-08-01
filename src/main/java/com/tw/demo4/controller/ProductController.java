package com.tw.demo4.controller;

import com.tw.demo4.entity.Product;
import com.tw.demo4.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAll(@RequestParam(name = "sort", defaultValue = "all") String sort,
                                                @RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                                                @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {

        List<Product> products = productService.getAll(pageNum, pageSize, sort);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> get(@PathVariable int id) {
        Optional<Product> product = productService.get(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity add(@RequestBody Product product) {
        Optional<Product> addedProduct = Optional.ofNullable(productService.add(product));
        if (addedProduct.isPresent()) {
            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.status(500).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody Product product, @PathVariable int id) {
        Product updatedProduct = productService.update(product, id);
        if (updatedProduct == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        boolean isDeleted = productService.delete(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
