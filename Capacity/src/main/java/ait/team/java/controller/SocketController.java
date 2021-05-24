package ait.team.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import ait.team.java.controller.output.ChatOutput;

@Controller
public class SocketController {
	@Autowired
	SimpMessagingTemplate simpMessagingTemplate;
	@MessageMapping("/comment/send/{id}")
	public void sendMessage(@Payload ChatOutput comment,@DestinationVariable long id) {
		simpMessagingTemplate.convertAndSend("/comment/send/public/"+id,comment);
	}
}
