package ru.geekbrains.service;

import ru.geekbrains.persist.Order;
import ru.geekbrains.persist.OrderRepository;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.stream.Collectors;


@Stateless
public class OrderServiceImpl implements OrderService{

  @EJB
  private OrderRepository orderRepository;

  @EJB
  private ProductRepository productRepository;

  @Override
  public List<OrderRepr> findAll(Long state) {
    return orderRepository.findAll(state).stream().map(OrderRepr::new).collect(Collectors.toList());
  }

  @Override
  public OrderRepr findById(Long id) {
    Order order = orderRepository.findById(id);
    return order == null ? null : new OrderRepr(order);
  }

  @TransactionAttribute
  @Override
  public void save(OrderRepr order) {
    orderRepository.save(new Order(order, order.getProductId() == null ? null : productRepository.getReference(order.getProductId())));
  }

  @TransactionAttribute
  @Override
  public void deleteById(Long id) {
    orderRepository.deleteById(id);
  }

  @TransactionAttribute
  @Override
  public void addToCart(Long productId) {
    Product product = productRepository.findById(productId);
    if (product != null){
      Order order = new Order();
      order.setState(0l);
      order.setProduct(product);
      orderRepository.save(order);
    }
  }
}
