package ru.geekbrains;

import ru.geekbrains.service.ProductServiceRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;

public class RemoteClient {
  public static void main(String[] args) {
    Properties env = new Properties();
    try {
      env.load(RemoteClient.class.getClassLoader().getResourceAsStream("wildfly-jndi.properties"));
      Context context = new InitialContext(env);
      ProductServiceRemote productService = (ProductServiceRemote) context.lookup("ejb:/homework6/ProductServiceImpl!ru.geekbrains.service.ProductServiceRemote");
      productService.findAll().forEach(productRepr -> System.out.println(productRepr.getName()));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
