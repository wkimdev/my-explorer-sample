package kr.co.doublechain.exp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@PropertySource("classpath:application.yml")
public class MyExplorerSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyExplorerSampleApplication.class, args);
	}
}
