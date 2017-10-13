package com.mahorad;

import com.mahorad.model.IVeggieRepository;
import com.mahorad.model.Veggie;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner setup(IVeggieRepository repository) {
        return (args) -> {
            repository.save(new Veggie("1", "Lettuce", new BigDecimal("7.80")));
            repository.save(new Veggie("2", "Cabbage", new BigDecimal("5.99")));
            repository.save(new Veggie("3", "Celery", new BigDecimal("9.92")));
            repository.save(new Veggie("4", "Tomato", new BigDecimal("11.20")));
            repository.save(new Veggie("5", "Broccoli", new BigDecimal("4.99")));
        };
    }
}