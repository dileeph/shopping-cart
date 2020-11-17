package org.ejb.sample.servlet.helper;

import org.ejb.session.model.Product;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.*;

public class MdbHelper {


    // Defines the JNDI context factory.
    public final static String JNDI_FACTORY="weblogic.jndi.WLInitialContextFactory";

    public final static String JMS_FACTORY="jms/ConnectionFactory";

    private static String url = "t3://localhost:7001";

    public static void sendMessage(List<Product> cartProducts) throws JMSException, NamingException {
        ConnectionFactory connectionFactory = (QueueConnectionFactory) getInitialContext().lookup(JMS_FACTORY);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        // Creating session for seding messages
        Session session = connection.createSession(false,
                Session.AUTO_ACKNOWLEDGE);

        Destination destination = (Destination) getInitialContext().lookup("jms/JmsQueue");
//      Destination destination = session.createQueue("jms/JmsQueue");

        MessageProducer mp = session.createProducer(destination);
        List<String> productNames = new ArrayList<String>();
        cartProducts.forEach(p -> productNames.add(p.getDescription()));

        TextMessage textMessage = session.createTextMessage("----Product persistence message for : " + Arrays.toString(productNames.toArray()));

        mp.send(textMessage);
    }



    private static InitialContext getInitialContext()    throws NamingException
    {
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, JNDI_FACTORY);
        env.put(Context.PROVIDER_URL, url);
        return new InitialContext(env);
    }
}
