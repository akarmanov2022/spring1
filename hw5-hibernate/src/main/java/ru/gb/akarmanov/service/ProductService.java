package ru.gb.akarmanov.service;

import ru.gb.akarmanov.dao.ProductRepository;
import ru.gb.akarmanov.model.Product;

import java.util.List;

public class ProductService {
  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public List<Product> findAllByCustomerId(Long customerId) {
    return productRepository.findAllByCustomerId(customerId);
  }
}
