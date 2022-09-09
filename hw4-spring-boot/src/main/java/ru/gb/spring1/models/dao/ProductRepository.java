package ru.gb.spring1.models.dao;

import ru.gb.spring1.models.Product;

import java.util.List;
import java.util.UUID;

public interface ProductRepository {
  List<Product> findAll();

  Product findById(UUID id);

  void insert(Product product);

  void update(Product product);

  void delete(UUID id);
}
