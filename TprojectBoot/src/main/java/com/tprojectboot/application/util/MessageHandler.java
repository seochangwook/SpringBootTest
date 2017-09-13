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
		return message;
	}
	
	//각각의 메세지 유형에 따라 Mapping을 추가해줄 수 있다.//
}
