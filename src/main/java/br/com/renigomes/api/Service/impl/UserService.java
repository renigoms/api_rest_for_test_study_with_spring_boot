package br.com.renigomes.api.Service.impl;

import br.com.renigomes.api.Service.UserServiceI;
import br.com.renigomes.api.Service.exceptions.ObjectNotFoundException;
import br.com.renigomes.api.domain.DTO.UserDTO;
import br.com.renigomes.api.domain.User;
import br.com.renigomes.api.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserServiceI {

    private final UserRepository userRepository;
    @Override
    public User findByID(Integer id) {
        Optional<User> userFind = userRepository.findById(id);
        return userFind.orElseThrow(() -> new ObjectNotFoundException("User not found!"));
    }

    @Override
    public List<User> findAll(){
        return userRepository.findAll();
    }
}
