package org.zsl.web.modules.test.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zsl.web.modules.test.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}