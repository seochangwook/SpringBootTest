package com.tprojectboot.application.util;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import com.tprojectboot.application.vo.MessageVO;

@RestController
public class MessageHandler {
	@MessageMapping("/hello") //설정한 prefix를 포함하면 /app/hello이다.//
	@SendTo("/topic/roomId") //전달할려는 곳의 subscribe//
	public MessageVO broadcasting(MessageVO message) throws Exception{
		System.out.println("message: " + message.getSendMessage());
		return message;
	}
	
	//각각의 메세지 유형에 따라 Mapping을 추가해줄 수 있다.//
	@MessageMapping("/out") //설정한 prefix를 포함하면 /app/hello이다.//
	@SendTo("/topic/out") //전달할려는 곳의 subscribe//
	public String outroom(String message) throws Exception{
		System.out.println("out message: " + message);
		return message;
	}
	
	@MessageMapping("/in") //설정한 prefix를 포함하면 /app/hello이다.//
	@SendTo("/topic/in") //전달할려는 곳의 subscribe//
	public String inroom(String message) throws Exception{
		System.out.println("in message: " + message);
		return message;
	}
}
