package kr.co.doublechain.exp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@EnableWebSocketMessageBroker
@Configuration
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
    	// broker의 대상 목적지에 대한 profix
    	config.enableSimpleBroker("/topic");
    	// server controller에서 사용할 profix
        config.setApplicationDestinationPrefixes("/app");
    }
    
    // client가 접속할 server의 url
    // web browser에서 connect버튼 클릭시 다음 요청
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocket")
    			.setAllowedOrigins("http://localhost:8081") // 이 경로에서 요청하는걸 허락.
        		.withSockJS();
    }

}
