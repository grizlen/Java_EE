package ru.geekbrains.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CategoryRepository {

  private static  final Logger logger = LoggerFactory.getLogger(CategoryRepository.class);

  @PersistenceContext(unitName = "ds")
  private EntityManager em;

  public Long countAll(){
    return em.createNamedQuery("Category.countAll", Long.class).getSingleResult();
  }

  public List<Category> findAll() {
    return em.createNamedQuery("Category.findAll", Category.class).getResultList();
  }

  public Category findById(Long id) {
    return em.find(Category.class, id);
  }

  public Category getReference(Long id){
    return em.getReference(Category.class, id);
  }

  public void save(Category category) {
    if (category.getId() == null) {
      em.persist(category);
    }
    em.merge(category);
  }

  public void deleteById(Long id) {
    em.createNamedQuery("Category.deleteById").setParameter("id", id).executeUpdate();
  }

}
