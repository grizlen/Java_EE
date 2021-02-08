package ru.geekbrains.javaee.hw2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/page_header")
public class PageHeaderServlet extends HttpServlet {
  private final String[] pages = {
      "main",
      "catalog",
      "product",
      "cart",
      "order"
  };
  private final String[] titles = {
      "Главная страница",
      "Каталог товаров",
      "Товар",
      "Корзина",
      "Оформить заказ"
  };
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Integer index = (Integer) req.getAttribute("pageindex");
    PrintWriter writer = resp.getWriter();
    writer.println("<h1>" + titles[index] + "</h1>");
    writer.println("<ul>");
    for (int i = 0; i < pages.length; i++){
      writer.println(String.format("<li><a href=\"%s\">%s</a></li>", pages[i], titles[i]));
    }
    writer.println("</ul>");
  }
}
