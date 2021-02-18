package ru.geekbrains.javaee.hw3;

import ru.geekbrains.javaee.hw3.persist.Category;
import ru.geekbrains.javaee.hw3.persist.CategoryRepository;
import ru.geekbrains.javaee.hw3.persist.User;
import ru.geekbrains.javaee.hw3.persist.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/user/*"})
public class UserServlet extends HttpServlet {
  private UserRepository userRepository;

  @Override
  public void init() throws ServletException {
    userRepository = (UserRepository) getServletContext().getAttribute("userRepository");
    if (userRepository == null){
      throw new ServletException("userRepository not initialized.");
    }
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    if (req.getPathInfo() == null || req.getPathInfo().equals("/")){
      req.setAttribute("users", userRepository.findAll());
      getServletContext().getRequestDispatcher("/WEB-INF/user.jsp").forward(req, resp);
    }
    else if (req.getPathInfo().equals("/edit")){
      Long id;
      try {
        id = Long.parseLong(req.getParameter("id"));
      } catch (NumberFormatException ex){
        resp.setStatus(400);
        return;
      }
      User user = userRepository.findById(id);
      if (user == null){
        resp.setStatus(404);
        return;
      }
      req.setAttribute("user", user);
      getServletContext().getRequestDispatcher("/WEB-INF/user_form.jsp").forward(req, resp);
    }
    else if (req.getPathInfo().equals("/delete")) {
      Long id;
      try {
        id = Long.parseLong(req.getParameter("id"));
      } catch (NumberFormatException ex) {
        resp.setStatus(400);
        return;
      }
      userRepository.deleteById(id);
      resp.sendRedirect(getServletContext().getContextPath() + "/user");
    }
    else if (req.getPathInfo().equals("/new")){
      req.setAttribute("user", new User());
      getServletContext().getRequestDispatcher("/WEB-INF/user_form.jsp").forward(req, resp);
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
    User user = new User(id, req.getParameter("name"));
    userRepository.saveOrUpdate(user);
    resp.sendRedirect(getServletContext().getContextPath() + "/user/");
  }
}
