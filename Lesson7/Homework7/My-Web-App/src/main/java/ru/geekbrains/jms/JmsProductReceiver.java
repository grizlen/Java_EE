package ru.geekbrains.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.services.ProductRepr;
import ru.geekbrains.services.ProductService;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@MessageDriven(
    activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:jboss/exported/jms/productQueue")
    }
)
public class JmsProductReceiver implements MessageListener {

  private final Logger logger = LoggerFactory.getLogger(JmsProductReceiver.class);

  @EJB
  ProductService productService;

  @Override
  public void onMessage(Message message) {
    logger.info("New JMS Message.");
    if (message instanceof ObjectMessage) {
      ObjectMessage om = (ObjectMessage) message;
      try {
        productService.save((ProductRepr) om.getObject());
      } catch (JMSException e) {
        e.printStackTrace();
        logger.error("", e);
      }
    }
  }
}
