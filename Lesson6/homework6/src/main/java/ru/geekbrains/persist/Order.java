package ru.geekbrains.persist;

import ru.geekbrains.service.OrderRepr;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@NamedQueries({
    @NamedQuery(name = "Order.findAll", query = "from Order o where o.state = :state"),
    @NamedQuery(name = "Order.deleteById", query = "delete from Order o where o.id = :id")
})
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private Long state;

  @ManyToOne()
  private Product product;

  public Order() {
  }

  public Order(Long id, Long state, Product product) {
    this.id = id;
    this.state = state;
    this.product = product;
  }
  public Order(OrderRepr orderRepr, Product product){
    this(orderRepr.getId(), orderRepr.getState(), product);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getState() {
    return state;
  }

  public void setState(Long state) {
    this.state = state;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }
}
