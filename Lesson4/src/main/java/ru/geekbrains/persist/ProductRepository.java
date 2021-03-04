package ru.geekbrains.persist;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Named
@ApplicationScoped
public class ProductRepository {
  private final Map<Long, Product> productMap = new ConcurrentHashMap<>();

  @PostConstruct
  public void init(){
    this.saveOrUpdate(
        new Product(null, "Product 1", "Product 1 descrition", new BigDecimal(200)));
    this.saveOrUpdate(
        new Product(null, "Product 2", "Product 2 descrition", new BigDecimal(200)));
    this.saveOrUpdate(
        new Product(null, "Product 3", "Product 3 descrition", new BigDecimal(500)));
  }

  private final AtomicLong identity = new AtomicLong(0);

  public List<Product> findAll() {
    return new ArrayList<>(productMap.values());
  }

  public Product findById(Long id) {
    return productMap.get(id);
  }

  public void saveOrUpdate(Product product) {
    if (product.getId() == null) {
      Long id = identity.incrementAndGet();
      product.setId(id);
    }
    productMap.put(product.getId(), product);
  }

  public void deleteById(Long id) {
    productMap.remove(id);
  }
}
