package ru.geekbrains.services;

import java.util.List;

public interface ProductServiceRemote {
  List<ProductRepr> findAll();
  ProductRepr findById(Long id);
  Long countAll();
}
