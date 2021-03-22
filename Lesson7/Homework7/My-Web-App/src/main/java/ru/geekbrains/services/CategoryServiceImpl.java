package ru.geekbrains.services;

import ru.geekbrains.persist.Category;
import ru.geekbrains.persist.CategoryRepository;
import ru.geekbrains.rest.CategoryServiceRest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class CategoryServiceImpl implements CategoryService, CategoryServiceRest {

  @EJB
  CategoryRepository categoryRepository;

  @Override
  public Long countAll() {
    return categoryRepository.countAll();
  }

  @Override
  public List<CategoryRepr> findAll() {
    return categoryRepository.findAll().stream().map(this::categoryToCategoryRepr).collect(Collectors.toList());
  }

  private CategoryRepr categoryToCategoryRepr(Category category){
    CategoryRepr categoryRepr = new CategoryRepr();
    categoryRepr.setId(category.getId());
    categoryRepr.setName(category.getName());
    return categoryRepr;
  }

  @Override
  public CategoryRepr findById(Long id) {
    return categoryToCategoryRepr(categoryRepository.findById(id));
  }

  @TransactionAttribute
  @Override
  public void insert(CategoryRepr category) {
    if (category.getId() != null){
      throw new IllegalArgumentException();
    }
    save(category);
  }

  @Override
  public void update(CategoryRepr category) {
    if (category.getId() == null){
      throw new IllegalArgumentException();
    }
    save(category);
  }

  @TransactionAttribute
  @Override
  public void save(CategoryRepr category) {
    Category c = new Category();
    c.setId(category.getId());
    c.setName(category.getName());
    categoryRepository.save(c);
  }

  @TransactionAttribute
  @Override
  public void deleteById(Long id) {
    categoryRepository.deleteById(id);
  }
}
