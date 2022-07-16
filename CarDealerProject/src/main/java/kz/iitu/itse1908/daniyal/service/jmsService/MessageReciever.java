package kz.iitu.itse1908.daniyal.service.jmsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReciever {

    @JmsListener(destination = "OrderTransactionQueue", containerFactory = "myFactory")
    public void receiveMessage(String transaction) {
        System.out.println("Received[1] <" + transaction + ">");
    }

    @JmsListener(destination = "OrderTransactionQueue", containerFactory = "myFactory")
    public void receiveMessage2(String transaction) {
        System.out.println("Received[2] <" + transaction + ">");
    }

    @JmsListener(destination = "OrderTransactionQueue", containerFactory = "myFactory")
    public void receiveMessage3(String transaction) {
        System.out.println("Received[3] <" + transaction + ">");
    }
}
