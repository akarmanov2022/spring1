package ru.gb.akarmanov;

import org.hibernate.cfg.Configuration;
import ru.gb.akarmanov.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Main {
  public static void main(String[] args) {
    EntityManagerFactory sessionFactory = new Configuration()
        .configure()
        .buildSessionFactory();

    EntityManager em = sessionFactory.createEntityManager();

    em.getTransaction().begin();

    em.persist(new Product("p1", 100));

    em.getTransaction().commit();

    em.close();
  }
}
