package ru.gb.akarmanov.dao;

import org.springframework.stereotype.Repository;
import ru.gb.akarmanov.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
  private final Map<UUID, Product> productMap = new ConcurrentHashMap<>();

  @Override
  public List<Product> findAll() {
    return new ArrayList<>(productMap.values());
  }

  @Override
  public Product findById(UUID id) {
    return productMap.get(id);
  }

  @Override
  public void insert(Product product) {
    UUID id = UUID.randomUUID();
    product.setId(id);
    productMap.put(id, product);
  }

  @Override
  public void update(Product product) {
    productMap.put(product.getId(), product);
  }

  @Override
  public void delete(UUID id) {
    productMap.remove(id);
  }
}
