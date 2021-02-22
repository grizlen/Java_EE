package ru.geekbrains.javaee.hw3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.javaee.hw3.persist.Category;
import ru.geekbrains.javaee.hw3.persist.CategoryRepository;
import ru.geekbrains.javaee.hw3.persist.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(urlPatterns = {"/category/*"})
public class CategoryServlet extends HttpServlet {
  private CategoryRepository categoryRepository;

  @Override
  public void init() throws ServletException {
    categoryRepository = (CategoryRepository) getServletContext().getAttribute("categoryRepository");
    if (categoryRepository == null){
      throw new ServletException("categoryRepository not initialized.");
    }
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    if (req.getPathInfo() == null || req.getPathInfo().equals("/")){
      req.setAttribute("categorys", categoryRepository.findAll());
      getServletContext().getRequestDispatcher("/WEB-INF/category.jsp").forward(req, resp);
    }
    else if (req.getPathInfo().equals("/edit")){
      Long id;
      try {
        id = Long.parseLong(req.getParameter("id"));
      } catch (NumberFormatException ex){
        resp.setStatus(400);
        return;
      }
      Category category = categoryRepository.findById(id);
      if (category == null){
        resp.setStatus(404);
        return;
      }
      req.setAttribute("category", category);
      getServletContext().getRequestDispatcher("/WEB-INF/category_form.jsp").forward(req, resp);
    }
    else if (req.getPathInfo().equals("/delete")) {
      Long id;
      try {
        id = Long.parseLong(req.getParameter("id"));
      } catch (NumberFormatException ex) {
        resp.setStatus(400);
        return;
      }
      categoryRepository.deleteById(id);
      resp.sendRedirect(getServletContext().getContextPath() + "/category");
    }
    else if (req.getPathInfo().equals("/new")){
      req.setAttribute("category", new Category());
      getServletContext().getRequestDispatcher("/WEB-INF/category_form.jsp").forward(req, resp);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Long id = null;
    if (req.getParameter("id") != null && !req.getParameter("id").isEmpty()) {
      try {
        id = Long.parseLong(req.getParameter("id"));
      } catch (NumberFormatException ex) {
        resp.setStatus(400);
        return;
      }
    }
    Category category = new Category(id, req.getParameter("name"));
    categoryRepository.saveOrUpdate(category);
    resp.sendRedirect(getServletContext().getContextPath() + "/category/");
  }
}
