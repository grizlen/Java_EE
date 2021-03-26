package ru.geekbrains.services;

import ru.geekbrains.persist.RoleRepository;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class RoleService implements Serializable {
  @Inject
  RoleRepository roleRepository;

  @TransactionAttribute
  public List<RoleRepr> getAllRoles(){
    return roleRepository.findAll().stream().map(RoleRepr::new).collect(Collectors.toList());
  }
}
