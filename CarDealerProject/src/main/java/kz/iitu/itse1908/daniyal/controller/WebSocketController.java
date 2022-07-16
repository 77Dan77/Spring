package kz.iitu.itse1908.daniyal.controller;

import kz.iitu.itse1908.daniyal.database.Message;
import kz.iitu.itse1908.daniyal.database.OutputMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@EnableWebMvc
public class WebSocketController{

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public OutputMessage send(Message message) throws Exception {
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new OutputMessage(message.getFrom(), message.getText(), time);
    }

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("socketPage");
        return modelAndView;
    }
}
