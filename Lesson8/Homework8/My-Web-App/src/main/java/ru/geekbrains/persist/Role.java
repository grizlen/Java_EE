package ru.geekbrains.persist;

import ru.geekbrains.services.RoleRepr;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "roles")
@NamedQueries({
    @NamedQuery(name = "Role.countAll", query = "select count(*) from Role"),
    @NamedQuery(name = "Role.findAll", query = "from Role")
})
public class Role implements Serializable {
  @Id
  @NotNull
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotNull
  @Column(name = "name")
  private String name;

  @ManyToMany(mappedBy = "roles")
  private Set<User> users;

  public Role() {
  }

  public Role(@NotNull String name) {
    this.name = name;
  }

  public Role(RoleRepr roleRepr) {
    id = roleRepr.getId();
    name = roleRepr.getName();
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<User> getUsers() {
    return users;
  }

  public void setUsers(Set<User> users) {
    this.users = users;
  }
}
