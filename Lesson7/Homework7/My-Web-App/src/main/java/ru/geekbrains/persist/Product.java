package ru.geekbrains.persist;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@NamedQueries({
    @NamedQuery(name = "Product.countAll", query = "select count(*) from Product"),
    @NamedQuery(name = "Product.findAll", query = "from Product"),
    @NamedQuery(name = "Product.deleteById", query = "delete from Product p where p.id = :id"),
    @NamedQuery(name = "Product.findByName", query = "select p from Product p where p.name = :name"),
    @NamedQuery(name = "Product.findByCategoryId", query = "select p from Product p where p.category.id = :catId")
})
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String name;

  @Column(length = 1024)
  private String description;

  @Column
  private BigDecimal prise;

  @ManyToOne
  private Category category;

  public Product() {
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BigDecimal getPrise() {
    return prise;
  }

  public void setPrise(BigDecimal prise) {
    this.prise = prise;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }
}
