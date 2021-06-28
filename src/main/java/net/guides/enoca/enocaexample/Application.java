package net.guides.enoca.enocaexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		//ElasticSearchBinded myElastic = new ElasticSearchBinded();
		//myElastic.elasticSearch();
	}
}
