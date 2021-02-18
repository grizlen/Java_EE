package ru.geekbrains.javaee.hw3.listener;

import ru.geekbrains.javaee.hw3.persist.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class BootAppListener implements ServletContextListener {
  @Override
  public void contextInitialized(ServletContextEvent sce) {
    ProductRepository productRepository = new ProductRepository();
    productRepository.saveOrUpdate(
        new Product(null, "product 1", "product 1 desctiption", new BigDecimal(200)));
    productRepository.saveOrUpdate(
        new Product(null, "product 2", "product 2 desctiption", new BigDecimal(200)));
    productRepository.saveOrUpdate(
        new Product(null, "product 3", "product 2 desctiption", new BigDecimal(300)));
    sce.getServletContext().setAttribute("productRepository", productRepository);

    CategoryRepository categoryRepository = new CategoryRepository();
    categoryRepository.saveOrUpdate(
        new Category(null, "category 1"));
    categoryRepository.saveOrUpdate(
        new Category(null, "category 2"));
    categoryRepository.saveOrUpdate(
        new Category(null, "category 3"));
    sce.getServletContext().setAttribute("categoryRepository", categoryRepository);

    UserRepository userRepository = new UserRepository();
    userRepository.saveOrUpdate(
        new User(null, "user 1"));
    userRepository.saveOrUpdate(
        new User(null, "user 2"));
    userRepository.saveOrUpdate(
        new User(null, "user 3"));
    sce.getServletContext().setAttribute("userRepository", userRepository);
  }
}
