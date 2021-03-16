import ru.geekbrains.services.ProductRepr;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Properties;

public class JmsClient {
  public static void main(String[] args) throws IOException, NamingException {
    Context context = createInitialContext();
    ConnectionFactory factory =(ConnectionFactory) context.lookup("jms/RemoteConnectionFactory");
    JMSContext jmsContext = factory.createContext("jmsUser", "user");
    Destination destination = (Destination) context.lookup("jms/productQueue");
    JMSProducer producer = jmsContext.createProducer();

    ProductRepr product = new ProductRepr();
    product.setId(null);
    product.setName("JMS product");
    product.setDescription("product from JmsClient");
    product.setPrise(new BigDecimal(100));
    product.setCategoryId(1L);

    ObjectMessage om = jmsContext.createObjectMessage(product);
    producer.send(destination, om);
  }
  public static Context createInitialContext() throws IOException, NamingException {
    final Properties env = new Properties();
    env.load(JmsClient.class.getClassLoader().getResourceAsStream("wildfly-jndi.properties"));
    return new InitialContext(env);
  }
}
