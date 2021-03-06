package ru.geekbrains.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persist.*;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class OrderController implements Serializable {

  private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

  @Inject
  OrderRepository orderRepository;

  private Order order;
  private List<Order> orders;

  public void preLoadCart(ComponentSystemEvent componentSystemEvent){
    orders = orderRepository.findAll(0L);
  }
  public void preLoadOrders(ComponentSystemEvent componentSystemEvent){
    orders = orderRepository.findAll(1L);
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public List<Order> getAllOrders() {
    return orders;
  }

  public void addToCart(Product product){
    order = new Order();
    order.setState(0L);
    order.setProduct(product);
    orderRepository.save(order);
  }

  public void deleteOrder(Order order){
    orderRepository.deleteById(order.getId());
  }

  public void buyOrder(Order order){
    order.setState(1L);
    orderRepository.save(order);
  }
}
