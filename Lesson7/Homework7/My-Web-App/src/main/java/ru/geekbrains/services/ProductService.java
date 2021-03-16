package ru.geekbrains.services;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ProductService {
  List<ProductRepr> findAll();
  ProductRepr findById(Long id);
  Long countAll();
  void save(ProductRepr product);
  void deleteById(Long id);
}
