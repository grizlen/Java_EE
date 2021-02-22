package ru.geekbrains.javaee.hw3.errorpage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/ErrorPageServlet")
public class ErrorPageServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String message = (String) req.getAttribute("javax.servlet.error.message");
    Integer code = (Integer) req.getAttribute("javax.servlet.error.status_code");
    if (code != null){
      resp.getWriter().println("<h1>Код Ошибки: " + code + "</h1>");
    }
    resp.getWriter().println("<p>Сообщение: " + message + "</p>");
  }
}
