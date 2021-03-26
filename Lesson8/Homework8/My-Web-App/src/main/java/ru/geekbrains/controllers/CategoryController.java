package ru.geekbrains.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.services.CategoryRepr;
import ru.geekbrains.services.CategoryService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class CategoryController implements Serializable {

  private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

  @EJB
  private CategoryService categoryService;

  private CategoryRepr category;
  private List<CategoryRepr> categories;

  public void preLoadCategories(ComponentSystemEvent componentSystemEvent){
    categories = categoryService.findAll();
  }

  public CategoryRepr getCategory() {
    return category;
  }

  public void setCategory(CategoryRepr category) {
    this.category = category;
  }

  public List<CategoryRepr> getAllCategories() {
    return categories;
  }

  public String createCategory(){
    category = new CategoryRepr();
    return "/category_form.xhtml?faces-redirect=true";
  }

  public String editCategory(CategoryRepr category){
    this.category = category;
    return "/category_form.xhtml?faces-redirect=true";
  }

  public String saveCategory(){
    categoryService.save(category);
    return "/categories.xhtml?faces-redirect=true";
  }

  public void deleteCategory(CategoryRepr category) {
    categoryService.deleteById(category.getId());
  }
}
