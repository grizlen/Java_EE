package ru.geekbrains.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import java.util.List;

@Stateless
public class ProductRepository {

  private static  final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

  @PersistenceContext(unitName = "ds")
  private EntityManager em;

  public List<Product> findAll() {
    return em.createNamedQuery("Product.findAll", Product.class).getResultList();
  }

  public Product findById(Long id) {
    return em.find(Product.class, id);
  }

  public Product getReference(Long id){
    return em.getReference(Product.class, id);
  }

  public Long countAll(){
    return em.createNamedQuery("Product.countAll", Long.class).getSingleResult();
  }

  public void save(Product product) {
    if (product.getId() == null) {
      em.persist(product);
    }
    em.merge(product);
  }

  public void deleteById(Long id) {
    em.createNamedQuery("Product.deleteById").setParameter("id", id).executeUpdate();
  }
}
