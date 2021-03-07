package ru.geekbrains.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.math.BigDecimal;
import java.util.List;

@Named
@ApplicationScoped
public class ProductRepository {

  private static  final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

  @PersistenceContext(unitName = "ds")
  private EntityManager em;

  @Resource
  private UserTransaction ut;

  @PostConstruct
  public void init() throws SystemException, NotSupportedException {
    if (countAll() == 0) {
      try {
        ut.begin();
        this.save(
            new Product(null, "Product 1", "Product 1 descrition", new BigDecimal(200), null));
        this.save(
            new Product(null, "Product 2", "Product 2 descrition", new BigDecimal(200), null));
        this.save(
            new Product(null, "Product 3", "Product 3 descrition", new BigDecimal(500), null));
        ut.commit();
      } catch (Exception e) {
        logger.error("", e);
        ut.rollback();
      }
    }
  }

  public Long countAll(){
    return em.createNamedQuery("Product.countAll", Long.class).getSingleResult();
  }

  public List<Product> findAll() {
    return em.createNamedQuery("Product.findAll", Product.class).getResultList();
  }

  public Product findById(Long id) {
    return em.find(Product.class, id);
  }

  @Transactional
  public void save(Product product) {
    if (product.getId() == null) {
      em.persist(product);
    }
    em.merge(product);
  }

  @Transactional
  public void deleteById(Long id) {
    em.createNamedQuery("Product.deleteById").setParameter("id", id).executeUpdate();
  }

}
