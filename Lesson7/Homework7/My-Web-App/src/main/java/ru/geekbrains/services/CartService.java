package ru.geekbrains.services;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CartService {
  List<ProductRepr> allProducts();
  void addProduct(ProductRepr product);
  void removeProduct(ProductRepr product);
}
