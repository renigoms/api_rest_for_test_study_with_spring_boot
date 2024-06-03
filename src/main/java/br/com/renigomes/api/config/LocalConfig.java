package br.com.renigomes.api.config;

import br.com.renigomes.api.domain.Users;
import br.com.renigomes.api.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration

@AllArgsConstructor
public class LocalConfig {
    private final UserRepository userRepository;

    @Bean
    public CommandLineRunner startBD(){
        System.out.println("ta passando" );
        Users users = new Users(null, "Renan N Gomes",
                "renan.nic@hotmail.com", "12345");

        Users users2 = new Users(null, "Renata N Gomes",
                "renata.nic@hotmail.com", "12345");

        userRepository.saveAll(List.of(users, users2));

        return args -> {
        };
    }

}
