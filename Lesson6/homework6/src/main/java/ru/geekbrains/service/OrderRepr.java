package ru.geekbrains.service;

import ru.geekbrains.persist.Order;
import ru.geekbrains.persist.Product;

import java.io.Serializable;

public class OrderRepr implements Serializable {
  private Long id;

  private Long state;

  private Long productId;

  private String productName;

  public OrderRepr() {
  }

  public OrderRepr(Long id, Long state) {
    this.id = id;
    this.state = state;
  }
  public OrderRepr(Order order){
    this(order.getId(), order.getState());
    Product product = order.getProduct();
    productId = product == null ? null : product.getId();
    productName = product == null ? null : product.getName();
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

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }
}
