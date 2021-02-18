package ru.geekbrains.javaee.hw3;

import ru.geekbrains.javaee.hw3.persist.Product;
import ru.geekbrains.javaee.hw3.persist.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(urlPatterns = {"/product/*"})
public class ProductServlet extends HttpServlet {
  private ProductRepository productRepository;
  @Override
  public void init() throws ServletException {
    productRepository = (ProductRepository) getServletContext().getAttribute("productRepository");
    if (productRepository == null){
      throw new ServletException("productRepository not initialized");
    }
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    if (req.getPathInfo() == null || req.getPathInfo().equals("/")){
      req.setAttribute("products", productRepository.findAll());
      getServletContext().getRequestDispatcher("/WEB-INF/product.jsp").forward(req, resp);
    }
    else if (req.getPathInfo().equals("/edit")){
      Long id;
      try {
        id = Long.parseLong(req.getParameter("id"));
      } catch (NumberFormatException ex){
        resp.setStatus(400);
        return;
      }
      Product product = productRepository.findById(id);
      if (product == null){
        resp.setStatus(404);
        return;
      }
      req.setAttribute("product", product);
      getServletContext().getRequestDispatcher("/WEB-INF/product_form.jsp").forward(req, resp);
    }
    else if (req.getPathInfo().equals("/delete")){
      Long id;
      try {
        id = Long.parseLong(req.getParameter("id"));
      } catch (NumberFormatException ex){
        resp.setStatus(400);
        return;
      }
      productRepository.deleteById(id);
      resp.sendRedirect(getServletContext().getContextPath() + "/product");
    }
    else if (req.getPathInfo().equals("/new")){
      req.setAttribute("product", new Product());
      getServletContext().getRequestDispatcher("/WEB-INF/product_form.jsp").forward(req, resp);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Long id = null;
    BigDecimal prise = BigDecimal.valueOf(0);
    if (req.getParameter("id") != null && !req.getParameter("id").isEmpty()) {
      try {
        id = Long.parseLong(req.getParameter("id"));
      } catch (NumberFormatException ex) {
        resp.setStatus(400);
        return;
      }
    }
    try {
      prise = new BigDecimal(req.getParameter("prise"));
    } catch (NumberFormatException ex) {
      resp.setStatus(400);
      return;
    }
    Product product = new Product(id, req.getParameter("name"), req.getParameter("description"), prise);
    productRepository.saveOrUpdate(product);
    resp.sendRedirect(getServletContext().getContextPath() + "/product/");
  }
}
