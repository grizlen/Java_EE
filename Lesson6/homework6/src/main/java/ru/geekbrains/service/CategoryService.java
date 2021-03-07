package ru.geekbrains.service;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CategoryService {
  Long countAll();
  List<CategoryRepr> findAll();
  CategoryRepr findById(Long id);
  void save(CategoryRepr category);
  void deleteById(Long id);
}
