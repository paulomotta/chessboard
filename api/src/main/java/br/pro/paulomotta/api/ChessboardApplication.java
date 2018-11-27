package br.pro.paulomotta.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * This is the class to start the chessboard service application
 * @author paulo
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages={"br.pro.paulomotta.api"})
@EnableJpaRepositories(basePackages="br.pro.paulomotta.api.repository")
@EnableTransactionManagement
@EntityScan(basePackages="br.pro.paulomotta.api.model")
public class ChessboardApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChessboardApplication.class, args);
    }
}
