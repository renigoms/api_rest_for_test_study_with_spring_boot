package br.com.renigomes.api.resources;

import br.com.renigomes.api.Service.impl.UserService;
import br.com.renigomes.api.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renigomes.api.domain.User;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserResources {

    private final UserService userService;
    @GetMapping("/{id}")
    public ResponseEntity<User> findByID(@PathVariable Integer id){
        return ResponseEntity.ok(userService.findByID(id));
    }
}
