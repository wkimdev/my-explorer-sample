package kr.co.doublechain.exp.domain;

import lombok.Getter;

/*
 * 서버에서 클라이언트로 응답 할 때 사용할 객체 정의 
 * 
 */
@Getter
public class Greeting {
	
	private String content;
	
	public Greeting() {
	}
	
	public Greeting(String content) {
		this.content = content;
	}
	
}
