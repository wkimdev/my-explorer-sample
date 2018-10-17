package kr.co.doublechain.exp.controller;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import kr.co.doublechain.exp.domain.Greeting;
import kr.co.doublechain.exp.domain.Message;

@Controller
public class ChatController {
	
	private static final Logger logger = LoggerFactory.getLogger(ChatController.class);
	
	// message를 요청할 경우의 uri
//	@MessageMapping("/hello")  // 요청 토픽이 "/hello"일 때 처리할 함수. 
//    @SendTo("/topic/greetings")  // 응답을 보낼 토픽 주소
//    public Greeting greeting() throws Exception {
//        //Thread.sleep(1000); // simulated delay
//        
//        // Greeting 클래스로 응답을 전달. json 형태로 전달됨.
//        //return new Greeting("Hello, " + message.getName() + "!");
//		return new Greeting("Hello, this is server message !");
//    }
	
	  @SendTo("/subscribe/broadcast")  // 응답을 보낼 토픽 주소
	  public Greeting greeting() throws Exception {
	      //Thread.sleep(1000); // simulated delay
	      
	      // Greeting 클래스로 응답을 전달. json 형태로 전달됨.
	      //return new Greeting("Hello, " + message.getName() + "!");
			return new Greeting("Hello, this is server message !");
	  }
	
}
