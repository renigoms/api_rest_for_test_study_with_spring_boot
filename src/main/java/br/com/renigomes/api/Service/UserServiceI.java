package br.com.renigomes.api.Service;

import br.com.renigomes.api.domain.User;

import java.util.List;

public interface UserServiceI {
     User findByID(Integer id);

     List<User> findAll();
}
