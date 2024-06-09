package br.com.renigomes.api.resources;

import br.com.renigomes.api.Service.impl.UserService;
import br.com.renigomes.api.domain.DTO.UserDTO;
import br.com.renigomes.api.domain.Users;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/user")

public class UserResources {

    public static final String ID = "/{id}";

    @Autowired
    private  UserService userService;
    @Autowired
    private  ModelMapper modelMapper;


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

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        return ResponseEntity.ok(userService.findAll().stream().map(
                                user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList())
        );

    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO obj){
        Users newObj = userService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = ID)
    public ResponseEntity<UserDTO> update(@PathVariable Integer id,
                                          @RequestBody UserDTO userDTO){
        userDTO.setId(id);
        return ResponseEntity.ok(
                modelMapper.map(
                        userService.update(userDTO), UserDTO.class
                )
        );
    }

    @DeleteMapping(value = ID)
    public ResponseEntity<UserDTO> delete(@PathVariable Integer id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
