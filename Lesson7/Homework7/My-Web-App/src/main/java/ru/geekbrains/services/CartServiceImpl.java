package ru.geekbrains.services;

import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateful
public class CartServiceImpl implements CartService{
  private final Map<Long, ProductRepr> products = new HashMap<>();

  @Override
  public List<ProductRepr> allProducts() {
    return new ArrayList<>(products.values());
  }

  @Override
  public void addProduct(ProductRepr product) {
    if (product.getId() != null){
      products.put(product.getId(), product);
    }
  }

  @Override
  public void removeProduct(ProductRepr product) {
    if (product.getId() != null){
      products.remove(product.getId());
    }
  }
}
