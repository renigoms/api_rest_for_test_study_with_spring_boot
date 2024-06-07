package br.com.renigomes.api.Service.impl;

import br.com.renigomes.api.Service.UserServiceI;
import br.com.renigomes.api.Service.exceptions.DataInterativeViolationException;
import br.com.renigomes.api.Service.exceptions.ObjectNotFoundException;
import br.com.renigomes.api.domain.DTO.UserDTO;
import br.com.renigomes.api.domain.Users;
import br.com.renigomes.api.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceI {

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private  ModelMapper modelMapper;
    @Override
    public Users findByID(Integer id) {
        Optional<Users> userFind = userRepository.findById(id);
        return userFind.orElseThrow(() -> new ObjectNotFoundException("Users not found!"));
    }

    @Override
    public List<Users> findAll(){
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public Users create(UserDTO userDTO) {
        findByEmail(userDTO);
        return userRepository.save(modelMapper.map(userDTO, Users.class));
    }

    @Transactional
    @Override
    public Users update(UserDTO userDTO) {
        findByEmail(userDTO);
        return userRepository.save(modelMapper.map(userDTO, Users.class));
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        findByID(id);
        userRepository.deleteById(id);
    }

    private void findByEmail(UserDTO userDTO){
        Optional<Users> user = userRepository.findByEmail(userDTO.getEmail());
        if (user.isPresent() && !user.get().getId().equals(userDTO.getId()))
            throw new DataInterativeViolationException("E-mail já cadastrado no sistema !");
    }
}
