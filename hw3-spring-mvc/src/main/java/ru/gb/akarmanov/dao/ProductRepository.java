package ru.gb.akarmanov.dao;

import ru.gb.akarmanov.models.Product;

import java.util.List;
import java.util.UUID;

public interface ProductRepository {
  List<Product> findAll();

  Product findById(UUID id);

  void insert(Product product);

  void update(Product product);

  void delete(UUID id);
}
