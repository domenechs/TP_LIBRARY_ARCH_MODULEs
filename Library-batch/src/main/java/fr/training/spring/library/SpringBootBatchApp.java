package fr.training.spring.library;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableBatchProcessing
public class SpringBootBatchApp {
    public static void main(String[] args) {
        final ApplicationContext context = SpringApplication.run(SpringBootBatchApp.class,args);
        System.exit(SpringApplication.exit(context));
    }
}
