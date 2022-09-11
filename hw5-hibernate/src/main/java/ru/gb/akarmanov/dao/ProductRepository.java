package ru.gb.akarmanov.dao;

import ru.gb.akarmanov.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ProductRepository implements EntityRepository<Product> {
  private final EntityManagerFactory entityManagerFactory;

  public ProductRepository(EntityManagerFactory entityManagerFactory) {
    this.entityManagerFactory = entityManagerFactory;
  }

  @Override
  public List<Product> findAll() {
    EntityManager em = entityManagerFactory.createEntityManager();
    List<Product> products = em.createQuery("SELECT p FROM Product p", Product.class)
        .getResultList();
    em.close();
    return products;
  }

  @Override
  public Product findById(Long id) {
    EntityManager em = entityManagerFactory.createEntityManager();
    Product product = em.find(Product.class, id);
    em.close();
    return product;
  }

  @Override
  public void deleteById(Long id) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    Product product = findById(id);
    em.remove(product);
    em.getTransaction().commit();
    em.close();
  }

  @Override
  public Optional<Product> saveOrUpdate(Product product) {
    EntityManager em = entityManagerFactory.createEntityManager();
    if (!Objects.isNull(product.getId())) {
      Product save = em.find(Product.class, product.getId());
      save.setPrice(product.getPrice());
      save.setTitle(product.getTitle());
    } else {
      em.getTransaction().begin();
      em.persist(product);
      em.getTransaction().commit();
    }
    Optional<Product> productOptional = Optional.of(em.find(Product.class, product.getId()));
    em.close();
    return productOptional;
  }
}
