package kr.kernel360.anabada;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class E2e1AnabadaApplication {

    public static void main(String[] args) {
        SpringApplication.run(E2e1AnabadaApplication.class, args);
    }

}
