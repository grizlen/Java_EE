package ru.geekbrains.service;

import ru.geekbrains.persist.CategoryRepository;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ProductServiceImpl implements ProductService, ProductServiceRemote{

  @EJB
  private ProductRepository productRepository;

  @EJB
  private CategoryRepository categoryRepository;

  @Override
  public List<ProductRepr> findAll() {
    return productRepository.findAll().stream().map(ProductRepr::new).collect(Collectors.toList());
  }

  @Override
  public ProductRepr findById(Long id) {
    Product product = productRepository.findById(id);
    return product == null ? null: new ProductRepr(product);
  }

  @Override
  public Long countAll() {
    return productRepository.countAll();
  }

  @TransactionAttribute
  @Override
  public void save(ProductRepr product) {
    productRepository.save(new Product(product, product.getCategoryId() == null ? null: categoryRepository.getReference(product.getCategoryId())));
  }

  @TransactionAttribute
  @Override
  public void deleteById(Long id) {
    productRepository.deleteById(id);
  }

}
