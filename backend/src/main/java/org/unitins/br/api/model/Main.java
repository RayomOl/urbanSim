package org.unitins.br.api.model;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.unitins.br")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
