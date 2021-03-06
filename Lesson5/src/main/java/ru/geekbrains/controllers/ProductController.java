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
public class ProductController implements Serializable {

  private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

  @Inject
  ProductRepository productRepository;

  @Inject
  CategoryRepository categoryRepository;

  private Product product;
  private List<Product> products;

  private Long catId;

  public void preLoadProducts(ComponentSystemEvent componentSystemEvent){
    products = productRepository.findAll();
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public List<Product> getAllProducts() {
    return products;
  }

  public String createProduct(){
    product = new Product();
    catId = null;
    return "/product_form.xhtml?faces-redirect=true";
  }

  public String editProduct(Product product){
    logger.info("Edit product");
    this.product = product;
    if (product.getCategory() == null) {
      catId = null;
    } else {
      catId = product.getCategory().getId();
    }
    return "/product_form.xhtml?faces-redirect=true";
  }

  public String getProductCategory(Product product){
    return product.getCategory() == null ? "no category": product.getCategory().getName();
  }

  public String saveProduct(){
    Category category = categoryRepository.findById(catId);
    product.setCategory(category);
    productRepository.save(product);
    return "/products.xhtml?faces-redirect=true";
  }

  public void deleteProduct(Product product) {
    productRepository.deleteById(product.getId());
  }

  public Long getCatId() {
    return catId;
  }

  public void setCatId(Long catId) {
    this.catId = catId;
  }
}
