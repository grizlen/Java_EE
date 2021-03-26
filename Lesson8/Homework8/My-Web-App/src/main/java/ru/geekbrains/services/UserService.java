package ru.geekbrains.services;

import ru.geekbrains.persist.User;
import ru.geekbrains.persist.UserRepository;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Local
@Stateless
public class UserService implements Serializable {
  @EJB
  UserRepository userRepository;

  @TransactionAttribute
  public void save(UserRepr userRepr){
    userRepository.save(new User(userRepr));
  }

  @TransactionAttribute
  public void delete(int id){
    userRepository.delete(id);
  }

  @TransactionAttribute
  public UserRepr findById(int id){
    return new UserRepr(userRepository.findById(id));
  }

  @TransactionAttribute
  public boolean existsById(int id){
    return userRepository.existsById(id);
  }

  @TransactionAttribute
  public List<UserRepr> getAll(){
    return userRepository.findAll().stream().map(UserRepr::new).collect(Collectors.toList());
  }
}
