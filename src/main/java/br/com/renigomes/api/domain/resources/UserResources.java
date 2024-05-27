package br.com.renigomes.api.domain.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renigomes.api.domain.User;

@RestController
@RequestMapping("/user")
public class UserResources {
    @GetMapping("/{id}")
    public ResponseEntity<User> findByID(@PathVariable Integer id){
        return ResponseEntity.ok(new User(id, "Renan N Gomes", "renan.nic@hotmail.com","sounan123"));
    }
}
