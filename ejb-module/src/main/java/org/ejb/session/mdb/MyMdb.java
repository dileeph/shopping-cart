package org.ejb.session.mdb;

import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.ejb.ActivationConfigProperty;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "connectionFactoryJndiName", propertyValue = "jms/ConnectionFactory"),
        @ActivationConfigProperty(propertyName = "destinationJndiName", propertyValue = "jms/JmsQueue") }, mappedName = "jms/JmsQueue")

public class MyMdb implements MessageListener {
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            System.out.println("nnt(mdb) MyMDB Received n" + textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}