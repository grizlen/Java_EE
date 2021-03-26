package ru.geekbrains.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.services.ProductRepr;
import ru.geekbrains.services.ProductService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ProductController implements Serializable {

  private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

  @EJB
  private ProductService productService;

  private ProductRepr product;
  private List<ProductRepr> products;

  public void preLoadProducts(ComponentSystemEvent componentSystemEvent){
    products = productService.findAll();
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

  public void deleteProduct(ProductRepr product) {
    productService.deleteById(product.getId());
  }
}
