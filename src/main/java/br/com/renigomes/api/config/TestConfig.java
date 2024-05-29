package br.com.renigomes.api.config;

import br.com.renigomes.api.domain.User;
import br.com.renigomes.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class TestConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public CommandLineRunner startBD(){
        User user = new User(null, "Renan N Gomes",
                "renan.nic@hotmail.com", "12345");

        User user2 = new User(null, "Renata N Gomes",
                "renata.nic@hotmail.com", "12345");

        userRepository.saveAll(List.of(user, user2));

        return args -> {};
    }
}
