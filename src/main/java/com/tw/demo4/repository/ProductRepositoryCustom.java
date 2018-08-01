package com.tw.demo4.repository;

import com.tw.demo4.entity.Product;

import java.util.List;

public interface ProductRepositoryCustom {
    List<Product> findAll(String brand, String category, Integer minPrice, Integer maxPrice, int pageNum, int pageSize, String order);
}
