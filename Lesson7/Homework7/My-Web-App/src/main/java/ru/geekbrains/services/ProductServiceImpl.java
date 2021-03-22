package ru.geekbrains.services;

import ru.geekbrains.persist.Category;
import ru.geekbrains.persist.CategoryRepository;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;
import ru.geekbrains.rest.ProductServiceRest;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Remote(ProductServiceRemote.class)
public class ProductServiceImpl implements ProductService, ProductServiceRemote, ProductServiceRest {

  @EJB
  private ProductRepository productRepository;

  @EJB
  private CategoryRepository categoryRepository;

  @Override
  public List<ProductRepr> findAll() {
    return productRepository.findAll().stream().map(this::productToProductRepr).collect(Collectors.toList());
  }

  private ProductRepr productToProductRepr(Product product){
    ProductRepr productRepr = new ProductRepr();
    productRepr.setId(product.getId());
    productRepr.setName(product.getName());
    productRepr.setDescription(product.getDescription());
    productRepr.setPrise(product.getPrise());
    Category category = product.getCategory();
    productRepr.setCategoryId(category == null ? null : category.getId());
    productRepr.setCategoryName(category == null ? null : category.getName());
    return productRepr;
  }

  @Override
  public ProductRepr findById(Long id) {
    Product product = productRepository.findById(id);
    return product == null ? null: productToProductRepr(product);
  }

  @Override
  public List<ProductRepr> findByName(String name) {
    return productRepository.findByName(name).stream().map(this::productToProductRepr).collect(Collectors.toList());
  }

  @Override
  public Long countAll() {
    return productRepository.countAll();
  }

  @TransactionAttribute
  @Override
  public void insert(ProductRepr product) {
    if (product.getId() != null){
      throw new IllegalArgumentException();
    }
    save(product);
  }

  @TransactionAttribute
  @Override
  public void update(ProductRepr product) {
    if (product.getId() == null){
      throw new IllegalArgumentException();
    }
    save(product);
  }

  @TransactionAttribute
  @Override
  public void save(ProductRepr product) {
    Product p = new Product();
    p.setId(product.getId());
    p.setName(product.getName());
    p.setDescription(product.getDescription());
    p.setPrise(product.getPrise());
    Category category = product.getCategoryId() == null ? null : categoryRepository.getReference(product.getCategoryId());
    p.setCategory(category);
    productRepository.save(p);
  }

  @TransactionAttribute
  @Override
  public void deleteById(Long id) {
    productRepository.deleteById(id);
  }

  @Override
  public List<ProductRepr> findByCategoryId(Long catId) {
    return productRepository.findByCategoryId(catId).stream().map(this::productToProductRepr).collect(Collectors.toList());
  }
}
