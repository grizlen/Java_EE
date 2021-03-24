package ru.geekbrains.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class UserRepository {

  private static  final Logger logger = LoggerFactory.getLogger(UserRepository.class);

  @PersistenceContext(unitName = "ds")
  private EntityManager em;

  public UserRepository() {
  }

  public Long countAll(){
    return em.createNamedQuery("User.countAll", Long.class).getSingleResult();
  }

  @TransactionAttribute
  public List<User> findAll() {
    return em.createNamedQuery("User.findAll", User.class).getResultList();
  }

  public User findById(int id){
    return em.find(User.class, id);
  }

  public boolean existsById(int id){
    return em.find(User.class, id) != null;
  }

  public User save(User user){
    if (user.getId() != null){
      em.persist(user);
      return user;
    }
    return em.merge(user);
  }

  @TransactionAttribute
  public void delete(int id){
    try {
      User attached = findById(id);
      if (attached != null) {
        em.remove(attached);
      }
    } catch (Exception e){
      logger.error("Error with entity class");
      throw new IllegalStateException(e);
    }
  }
}
