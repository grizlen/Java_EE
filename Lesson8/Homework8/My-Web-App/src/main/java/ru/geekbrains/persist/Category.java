package ru.geekbrains.persist;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
@NamedQueries({
    @NamedQuery(name = "Category.countAll", query = "select count(*) from Category"),
    @NamedQuery(name = "Category.findAll", query = "from Category"),
    @NamedQuery(name = "Category.deleteById", query = "delete from Category c where c.id = :id")
})
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String name;

  @OneToMany(mappedBy = "category")
  private List<Product> products;

  public Category() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }
}
