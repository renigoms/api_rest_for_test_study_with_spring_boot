package br.com.renigomes.api.Service.impl;

import br.com.renigomes.api.domain.DTO.UserDTO;
import br.com.renigomes.api.domain.Users;
import br.com.renigomes.api.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;


@SpringBootTest
@ExtendWith(value = MockitoExtension.class)
class UserServiceTest {

    public static final Integer ID = 1;
    public static final String NAME = "Renan Nicolau Gomes";
    public static final String EMAIL = "renan.nic@hotmail.com";
    public static final String PASSWORD = "12345";

    @InjectMocks
    private  UserService userService;

    @Mock
    private  UserRepository userRepository;

    @Mock
    private  ModelMapper modelMapper;


    private  Users users;

    private UserDTO userDTO;
    private Optional<Users> usersOptional;


    @BeforeEach
    void setUp() {
        startUsers();
    }

    @Test
    void whenFindByIdThenReturnAnUserIntance() {
        when(userRepository.findById(anyInt())).thenReturn(usersOptional);
        Users response = userService.findByID(ID);
        assertNotNull(response);
        assertEquals(Users.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUsers(){
        users = new Users(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL , PASSWORD);
        usersOptional =  Optional.of(new Users(1, NAME, EMAIL, PASSWORD));
    }
}