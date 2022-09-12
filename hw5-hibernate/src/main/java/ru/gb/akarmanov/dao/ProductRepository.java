package ru.gb.akarmanov.dao;

import ru.gb.akarmanov.model.Customer;
import ru.gb.akarmanov.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public class ProductRepository implements EntityRepository<Product> {
  private final EntityManagerFactory entityManagerFactory;

  public ProductRepository(EntityManagerFactory entityManagerFactory) {
    this.entityManagerFactory = entityManagerFactory;
  }

  @Override
  public List<Product> findAll() {
    return executeInEntityManager(em -> em.createQuery("SELECT p FROM Product p", Product.class).getResultList());
  }

  @Override
  public Optional<Product> findById(Long id) {
    Product product = executeInEntityManager(em -> em.find(Product.class, id));
    return Optional.of(product);
  }

  public List<Product> findAllByCustomerId(Long id) {
    Customer customer = executeInEntityManager(em -> em.find(Customer.class, id));
    return customer.getProducts();
  }

  @Override
  public void deleteById(Long id) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    findById(id).ifPresent(em::remove);
    em.getTransaction().commit();
    em.close();
  }

  @Override
  public Optional<Product> saveOrUpdate(Product product) {
    Product p = executeInEntityManager(em -> {
      if (Objects.isNull(product.getId())) em.persist(product);
      else em.merge(product);
      return em.find(Product.class, product.getId());
    });
    return Optional.of(p);
  }

  private <R> R executeInEntityManager(Function<EntityManager, R> function) {
    EntityManager em = entityManagerFactory.createEntityManager();
    try {
      return function.apply(em);
    } finally {
      em.close();
    }
  }
}
