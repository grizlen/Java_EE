package ru.geekbrains.controllers;

import ru.geekbrains.services.RoleRepr;
import ru.geekbrains.services.RoleService;
import ru.geekbrains.services.UserRepr;
import ru.geekbrains.services.UserService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Named
@Stateless
public class UserController {

  @EJB
  private UserService userService;

  @EJB
  private RoleService roleService;

  @Inject
  private HttpSession session;

  private UserRepr user;
  private List<UserRepr> users;

  private RoleRepr role;
  private List<RoleRepr> roles;

  public void preLoadUsers(ComponentSystemEvent componentSystemEvent){
    users = userService.getAll();
  }

  public void preLoadRoles(ComponentSystemEvent componentSystemEvent){
    roles = roleService.getAllRoles();
  }

  public UserRepr getUser() {
    return user;
  }

  public void setUser(UserRepr user) {
    this.user = user;
  }

  public List<UserRepr> getAllUsers() {
    return users;
  }

  public String editUser(UserRepr user){
    this.user = user;
    return "/admin/user_form.xhtml?faces-redirect=true";
  }

  public String saveUser(){
    userService.save(user);
    return "/admin/user.xhtml?faces-redirect=true";
  }

  public RoleRepr getRole() {
    return role;
  }

  public void setRole(RoleRepr role) {
    this.role = role;
  }

  public List<RoleRepr> getAllRoles() {
    return roles;
  }

  public String logout(){
//    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    session.invalidate();

    return "/main.xhtml?faces-redirect=true";
  }
  public boolean isAdmin(){
    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    return request.isUserInRole("admin");
  }
}
