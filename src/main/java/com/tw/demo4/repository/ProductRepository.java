package com.tw.demo4.repository;

import com.tw.demo4.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>, ProductRepositoryCustom {

}
