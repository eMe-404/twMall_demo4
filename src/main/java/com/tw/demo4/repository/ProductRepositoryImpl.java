package com.tw.demo4.repository;

import com.tw.demo4.entity.Product;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Product> findAll(String brand, String category, Integer minPrice, Integer maxPrice, int pageNum, int pageSize, String order) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Product> query = criteriaBuilder.createQuery(Product.class);
        final Root<Product> goodRoot = query.from(Product.class);

        Predicate predicate = constructSearchCondition(brand, category, minPrice, maxPrice, criteriaBuilder, goodRoot);
        Order priceOrder = order.equals("DESC") ? criteriaBuilder.desc(goodRoot.get("price")) : criteriaBuilder.asc(goodRoot.get("price"));

        final CriteriaQuery<Product> criteriaQuery = query.where(predicate).orderBy(priceOrder);
        return entityManager.createQuery(criteriaQuery).setFirstResult(pageNum * pageSize).setMaxResults(pageSize).getResultList();
    }

    private Predicate constructSearchCondition(String brand, String category, Integer minPrice, Integer maxPrice, CriteriaBuilder criteriaBuilder, Root<Product> goodRoot) {
        Predicate predicate = criteriaBuilder.conjunction();

        if (brand != null && !brand.isEmpty()) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(goodRoot.get("brand"), brand));
        }

        if (category != null && !category.isEmpty()) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(goodRoot.get("category"), category));
        }

        if (maxPrice != 0) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThan(goodRoot.get("price"), maxPrice));
        }

        if (minPrice != 0) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThan(goodRoot.get("price"), minPrice));
        }
        return predicate;
    }


}
