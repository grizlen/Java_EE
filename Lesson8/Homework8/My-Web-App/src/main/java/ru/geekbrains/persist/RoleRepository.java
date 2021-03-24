package ru.geekbrains.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class RoleRepository {

  private static  final Logger logger = LoggerFactory.getLogger(RoleRepository.class);

  @PersistenceContext(unitName = "ds")
  private EntityManager em;

  public Long countAll(){
    return em.createNamedQuery("Role.countAll", Long.class).getSingleResult();
  }

  public List<Role> findAll() {
    return em.createNamedQuery("Role.findAll", Role.class).getResultList();
  }

  public Role findById(int id){
    return em.find(Role.class, id);
  }

  @TransactionAttribute
  public Role merge(Role role){
    return em.merge(role);
  }
}
