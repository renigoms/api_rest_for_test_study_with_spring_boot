package br.com.renigomes.api.Service;

import br.com.renigomes.api.domain.DTO.UserDTO;
import br.com.renigomes.api.domain.Users;

import java.util.List;

public interface UserServiceI {
     Users findByID(Integer id);
     List<Users> findAll();
     Users create(UserDTO userDTO);
     Users update(UserDTO userDTO);
     void delete(Integer id);
}
