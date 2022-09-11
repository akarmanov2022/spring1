package ru.gb.akarmanov.dao;

import ru.gb.akarmanov.model.Product;

import java.util.List;
import java.util.Optional;

public interface EntityRepository<T> {
  List<T> findAll();

  T findById(Long id);

  void deleteById(Long id);

  Optional<Product> saveOrUpdate(T product);
}
