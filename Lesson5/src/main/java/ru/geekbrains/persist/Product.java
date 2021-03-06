package ru.geekbrains.persist;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
@NamedQueries({
    @NamedQuery(name = "Product.countAll", query = "select count(*) from Product"),
    @NamedQuery(name = "Product.findAll", query = "from Product"),
    @NamedQuery(name = "Product.deleteById", query = "delete from Product p where p.id = :id")
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

  @OneToMany(mappedBy = "product")
  private List<Order> orders;

  public Product() {
  }

  public Product(Long id, String name, String description, BigDecimal prise) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.prise = prise;
  }

  public Product(Long id, String name, String description, BigDecimal prise, Category category) {
    this(id, name, description,prise);
    this.category = category;
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

  public List<Order> getOrders() {
    return orders;
  }

  public void setOrders(List<Order> orders) {
    this.orders = orders;
  }
}
