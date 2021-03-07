package ru.geekbrains.service;

import javax.ejb.Local;
import java.util.List;

@Local
public interface OrderService {
  List<OrderRepr> findAll(Long state);
  OrderRepr findById(Long id);
  void save(OrderRepr order);
  void deleteById(Long id);
  void addToCart(Long productId);
}
