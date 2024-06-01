package br.com.renigomes.api.Service.impl;

import br.com.renigomes.api.Service.UserServiceI;
import br.com.renigomes.api.Service.exceptions.DataInterativeViolationException;
import br.com.renigomes.api.Service.exceptions.ObjectNotFoundException;
import br.com.renigomes.api.domain.DTO.UserDTO;
import br.com.renigomes.api.domain.User;
import br.com.renigomes.api.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserServiceI {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;
    @Override
    public User findByID(Integer id) {
        Optional<User> userFind = userRepository.findById(id);
        return userFind.orElseThrow(() -> new ObjectNotFoundException("User not found!"));
    }

    @Override
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User create(UserDTO userDTO) {
        findByEmail(userDTO);
        return userRepository.save(modelMapper.map(userDTO, User.class));
    }

    private void findByEmail(UserDTO userDTO){
        Optional<User> user = userRepository.findByEmail(userDTO.getEmail());
        if (user.isPresent())
            throw new DataInterativeViolationException("E-mail já cadastrado no sistema !");
    }
}
