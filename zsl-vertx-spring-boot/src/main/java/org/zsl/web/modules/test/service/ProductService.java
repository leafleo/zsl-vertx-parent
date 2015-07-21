package org.zsl.web.modules.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zsl.web.modules.test.dao.ProductRepository;
import org.zsl.web.modules.test.entity.Product;

@Service
public class ProductService {

  @Autowired
  private ProductRepository repo;

  public List<Product> getAllProducts() {
    return repo.findAll();
  }

}