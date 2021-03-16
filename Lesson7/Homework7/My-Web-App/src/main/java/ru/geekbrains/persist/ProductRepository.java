package ru.geekbrains.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProductRepository {
  private static  final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

  @PersistenceContext(unitName = "ds")
  private EntityManager em;

  public Long countAll(){
    return em.createNamedQuery("Product.countAll", Long.class).getSingleResult();
  }

  public List<Product> findAll() {
    return em.createNamedQuery("Product.findAll", Product.class).getResultList();
  }

  public Product findById(Long id) {
    return em.find(Product.class, id);
  }

  public List<Product> findByName(String name){
    return em.createNamedQuery("Product.findByName", Product.class).setParameter("name", name).getResultList();
  }

  public List<Product> findByCategoryId(Long catId){
    return em.createNamedQuery("Product.findByCategoryId", Product.class).setParameter("catId", catId).getResultList();
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
