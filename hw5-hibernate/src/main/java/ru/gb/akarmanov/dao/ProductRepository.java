package ru.gb.akarmanov.dao;

import ru.gb.akarmanov.model.Product;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ProductRepository implements EntityRepository<Product> {
  private final EntityManager em;

  public ProductRepository(EntityManager em) {
    this.em = em;
  }

  @Override
  public List<Product> findAll() {
    return em.createQuery("SELECT p FROM Product p", Product.class)
        .getResultList();
  }

  @Override
  public Product findById(Long id) {
    return em.find(Product.class, id);
  }

  @Override
  public void deleteById(Long id) {
    em.getTransaction().begin();
    Product product = findById(id);
    em.remove(product);
    em.getTransaction().commit();
  }

  @Override
  public Optional<Product> saveOrUpdate(Product product) {
    if (!Objects.isNull(product.getId())) {
      Product save = em.find(Product.class, product.getId());
      save.setPrice(product.getPrice());
      save.setTitle(product.getTitle());
    } else {
      em.getTransaction().begin();
      em.persist(product);
      em.getTransaction().commit();
    }
    return Optional.of(em.find(Product.class, product.getId()));
  }
}
