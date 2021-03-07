package ru.geekbrains.service;

import ru.geekbrains.persist.Category;
import ru.geekbrains.persist.CategoryRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class CategoryServiceImpl implements CategoryService{

  @EJB
  CategoryRepository categoryRepository;

  @Override
  public Long countAll(){
    return categoryRepository.countAll();
  }

  @Override
  public List<CategoryRepr> findAll(){
    return categoryRepository.findAll().stream().map(CategoryRepr::new).collect(Collectors.toList());
  }

  @Override
  public CategoryRepr findById(Long id){
    Category category = categoryRepository.findById(id);
    return category == null ? null : new CategoryRepr(category);
  }

  @TransactionAttribute
  @Override
  public void save(CategoryRepr category){
    categoryRepository.save(new Category(category));
  }

  @TransactionAttribute
  @Override
  public void deleteById(Long id){
    categoryRepository.deleteById(id);
  }
}
