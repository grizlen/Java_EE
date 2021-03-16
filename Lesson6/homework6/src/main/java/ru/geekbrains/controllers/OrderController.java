package ru.geekbrains.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.service.OrderRepr;
import ru.geekbrains.service.OrderService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class OrderController implements Serializable {

  private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

  @EJB
  OrderService orderService;

  private OrderRepr order;
  private List<OrderRepr> orders;

  public void preLoadCart(ComponentSystemEvent componentSystemEvent){
    orders = orderService.findAll(0L);
  }
  public void preLoadOrders(ComponentSystemEvent componentSystemEvent){
    orders = orderService.findAll(1L);
  }

  public OrderRepr getOrder() {
    return order;
  }

  public void setOrder(OrderRepr order) {
    this.order = order;
  }

  public List<OrderRepr> getAllOrders() {
    return orders;
  }

  public void addToCart(Long productId){
    orderService.addToCart(productId);
  }

  public void deleteOrder(OrderRepr order){
    orderService.deleteById(order.getId());
  }

  public void buyOrder(OrderRepr order){
    order.setState(1L);
    orderService.save(order);
  }
}
