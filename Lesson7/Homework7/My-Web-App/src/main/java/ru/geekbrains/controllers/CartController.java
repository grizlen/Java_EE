package ru.geekbrains.controllers;

import ru.geekbrains.services.CartService;
import ru.geekbrains.services.ProductRepr;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class CartController implements Serializable {

  @EJB
  CartService cartService;

  private ProductRepr product;
  private List<ProductRepr> products;

  public void preLoadProducts(ComponentSystemEvent componentSystemEvent) {
    products = cartService.allProducts();
  }

  public ProductRepr getProduct() {
    return product;
  }

  public void setProduct(ProductRepr product) {
    this.product = product;
  }

  public List<ProductRepr> getAllProducts() {
    return products;
  }

  public void addToCart(ProductRepr product){
    cartService.addProduct(product);
  }

  public void deleteProduct(ProductRepr product){
    cartService.removeProduct(product);
  }
}
