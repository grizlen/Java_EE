package ru.geekbrains.persist;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class OrderRepository {

  @PersistenceContext(unitName = "ds")
  private EntityManager em;

  public List<Order> findAll(Long state) {
    return em.createNamedQuery("Order.findAll", Order.class).setParameter("state", state).getResultList();
  }

  public Order findById(Long id) {
    return em.find(Order.class, id);
  }

  @Transactional
  public void save(Order order) {
    if (order.getId() == null) {
      em.persist(order);
    }
    em.merge(order);
  }

  @Transactional
  public void deleteById(Long id) {
    em.createNamedQuery("Order.deleteById").setParameter("id", id).executeUpdate();
  }

}
