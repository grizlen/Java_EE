package ru.geekbrains.services;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductRepr implements Serializable{
  private Long id;

  private String name;

  private String description;

  private BigDecimal prise;

  private Long categoryId;

  private String categoryName;

  public ProductRepr() {
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

  public Long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Long categoryId) {
    this.categoryId = categoryId;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }
}
