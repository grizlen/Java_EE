package ru.geekbrains.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persist.*;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class CategoryController implements Serializable {

  private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

  @Inject
  CategoryRepository categoryRepository;

  private Category category;
  private List<Category> categories;

  public void preLoadCategories(ComponentSystemEvent componentSystemEvent){
    categories = categoryRepository.findAll();
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public List<Category> getAllCategories() {
    return categories;
  }

  public String createCategory(){
    category = new Category();
    return "/category_form.xhtml?faces-redirect=true";
  }

  public String editCategory(Category category){
    this.category = category;
    return "/category_form.xhtml?faces-redirect=true";
  }

  public String saveCategory(){
    categoryRepository.save(category);
    return "/categories.xhtml?faces-redirect=true";
  }

  public void deleteCategory(Category category) {
    categoryRepository.deleteById(category.getId());
  }
}
