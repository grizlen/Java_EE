package ru.geekbrains.persist;

import ru.geekbrains.services.UserRepr;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@NamedQueries({
    @NamedQuery(name = "User.countAll", query = "select count(*) from User"),
    @NamedQuery(name = "User.findAll", query = "select distinct u from User u left join fetch u.roles")
})
public class User implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "login", unique = true, nullable = false)
  private String login;

  @Column(name = "password", nullable = false)
  private String password;

  @ManyToMany(cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
  })
  @JoinTable(name = "users_roles",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id")
  )
  private Set<Role> roles;

  public User() {
  }

  public User(Long id, String login, String password) {
    this.id = id;
    this.login = login;
    this.password = password;
  }

  public User(UserRepr userRepr) {
    id = userRepr.getId();
    login = userRepr.getLogin();
    password = userRepr.getPassword();
    roles = new HashSet<>();
    userRepr.getRoles().forEach(r -> roles.add(new Role(r)));
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }
}
