package kz.iitu.itse1908.daniyal.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import javax.jms.JMSException;
import javax.jms.Topic;


@RestController
@RequestMapping(value = "/api/jms")
public class JMSController {
    @Autowired
    JmsTemplate jmsTemplate;

    @PostMapping("/sendMessageTopic")
    public ResponseEntity<String> sendMessageTopic(@RequestBody String msg) throws JMSException { ///?
        jmsTemplate.convertAndSend("OrderTransactionQueue", msg);
        return new ResponseEntity<>("Message have been sended into topic!", HttpStatus.OK);
    }

}
