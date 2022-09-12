package ru.gb.akarmanov.dao;

import ru.gb.akarmanov.model.Customer;
import ru.gb.akarmanov.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class CustomerRepository implements EntityRepository<Customer> {
  private final EntityManagerFactory entityManagerFactory;

  public CustomerRepository(EntityManagerFactory entityManagerFactory) {
    this.entityManagerFactory = entityManagerFactory;
  }

  @Override
  public List<Customer> findAll() {
    return executeInEntityManager(em -> em.createQuery("SELECT c FROM Customer c", Customer.class)
        .getResultList());
  }

  @Override
  public Optional<Customer> findById(Long id) {
    return Optional.of(executeInEntityManager(em -> em.find(Customer.class, id)));
  }

  @Override
  public void deleteById(Long id) {
    executeTransaction(em -> em.createQuery("DELETE FROM Customer c WHERE c.id = :id")
        .setParameter("id", id));
  }

  @Override
  public Optional<Customer> saveOrUpdate(Customer product) {
    EntityManager em = entityManagerFactory.createEntityManager();
    if (Objects.isNull(product.getId())) {
      em.persist(product);
    } else {
      em.merge(product);
    }
    return findById(product.getId());
  }

  private <R> R executeInEntityManager(Function<EntityManager, R> function) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    try {
      return function.apply(entityManager);
    } finally {
      entityManager.close();
    }
  }

  private void executeTransaction(Consumer<EntityManager> consumer) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    consumer.accept(em);
    em.getTransaction().commit();
  }
}
