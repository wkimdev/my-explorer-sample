package kr.co.doublechain.exp.domain;

import lombok.Getter;
import lombok.Setter;

/*
 * 클라이언트가 서버로 보낼 요청 메세지 구조 
 * 
 */
@Getter
@Setter
public class Message {
	
	private String name;
	
	public Message() {
	}
	
	public Message(String name) {
		this.name = name;
	}

}
