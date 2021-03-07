package ru.geekbrains.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persist.*;
import ru.geekbrains.service.CategoryRepr;
import ru.geekbrains.service.CategoryService;
import ru.geekbrains.service.ProductRepr;
import ru.geekbrains.service.ProductService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ProductController implements Serializable {

  private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

  @EJB
  ProductService productService;

  @EJB
  CategoryService categoryService;

  private ProductRepr product;
  private List<ProductRepr> products;
  private List<CategoryRepr>categories;

  public void preLoadProducts(ComponentSystemEvent componentSystemEvent){
    products = productService.findAll();
    categories = categoryService.findAll();
  }

  public ProductRepr getProduct() {
    return product;
  }

  public void setProduct(ProductRepr product) {
    this.product = product;
  }

  public List<ProductRepr> getAllProducts() {
    return products;
  }

  public List<CategoryRepr> getCategories() {
    return categories;
  }

  public String createProduct(){
    product = new ProductRepr();
    return "/product_form.xhtml?faces-redirect=true";
  }

  public String editProduct(ProductRepr product){
    this.product = product;
    return "/product_form.xhtml?faces-redirect=true";
  }

  public String saveProduct(){
    productService.save(product);
    return "/products.xhtml?faces-redirect=true";
  }

  public void deleteProduct(Product product) {
    productService.deleteById(product.getId());
  }

}
