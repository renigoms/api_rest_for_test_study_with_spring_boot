package br.com.renigomes.api.Service.impl;

import br.com.renigomes.api.Service.exceptions.ObjectNotFoundException;
import br.com.renigomes.api.domain.DTO.UserDTO;
import br.com.renigomes.api.domain.Users;
import br.com.renigomes.api.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;


@SpringBootTest
@ExtendWith(value = MockitoExtension.class)
class UserServiceTest {

    public static final Integer ID = 1;
    public static final String NAME = "Renan Nicolau Gomes";
    public static final String EMAIL = "renan.nic@hotmail.com";
    public static final String PASSWORD = "12345";
    public static final String USERS_NOT_FOUND = "Users not found!";
    public static final int INDEX = 0;

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
    void whenFindByIdThenReturnAnObjectNotFoundException(){
        when(userRepository.findById(anyInt())).thenThrow(new ObjectNotFoundException(USERS_NOT_FOUND));
        ObjectNotFoundException thrown = assertThrows(
                ObjectNotFoundException.class, () -> userService.findByID(anyInt()));
        assertEquals(USERS_NOT_FOUND, thrown.getMessage());
    }

    @Test
    void whenFindAllThenReturnAnListOfUsers() {
        when(userRepository.findAll()).thenReturn(List.of(users));
        List<Users> response = userService.findAll();
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Users.class, response.get(INDEX).getClass());
        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME, response.get(INDEX).getName());
        assertEquals(EMAIL, response.get(INDEX).getEmail());
        assertEquals(PASSWORD, response.get(INDEX).getPassword());
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