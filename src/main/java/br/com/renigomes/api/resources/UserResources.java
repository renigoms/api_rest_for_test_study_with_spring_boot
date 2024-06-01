package br.com.renigomes.api.resources;

import br.com.renigomes.api.Service.impl.UserService;
import br.com.renigomes.api.domain.DTO.UserDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserResources {

    private final UserService userService;
    private final ModelMapper modelMapper;

    
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findByID(@PathVariable Integer id){
        return ResponseEntity.ok(
               modelMapper
                       .map(
                               userService.findByID(id),
                               UserDTO.class
                       )
        );
    }
}
