package ru.gb.spring1.models.dao;

import org.springframework.stereotype.Repository;
import ru.gb.spring1.models.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
  private final Map<UUID, Product> productMap = new ConcurrentHashMap<>();

  @PostConstruct
  private void init() {
    insert(new Product("P-1", 60.0));
    insert(new Product("P-2", 3620.0));
    insert(new Product("P-3", 602.0));
    insert(new Product("P-4", 360.10));
    insert(new Product("P-5", 160.0));
    insert(new Product("P-8", 60.0));
  }

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
    if (product.getId() != null)
      productMap.put(product.getId(), product);
    else
      insert(product);
  }

  @Override
  public void delete(UUID id) {
    productMap.remove(id);
  }
}
