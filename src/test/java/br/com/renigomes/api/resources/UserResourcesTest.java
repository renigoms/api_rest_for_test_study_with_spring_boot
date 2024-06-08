package br.com.renigomes.api.resources;

import br.com.renigomes.api.Service.impl.UserService;
import br.com.renigomes.api.domain.DTO.UserDTO;
import br.com.renigomes.api.domain.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserResourcesTest {
    public static final Integer ID = 1;
    public static final String NAME = "Renan Nicolau Gomes";
    public static final String EMAIL = "renan.nic@hotmail.com";
    public static final String PASSWORD = "12345";
    public static final String USERS_NOT_FOUND = "Users not found!";
    public static final int INDEX = 0;
    public static final String E_MAIL_JA_CADASTRADO_NO_SISTEMA = "E-mail já cadastrado no sistema !";
    private Users user;
    private UserDTO userDTO;
    Optional<Users> usersOptional;

    @InjectMocks
    private UserResources userResources;

    @Mock
    private UserService userService;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        startUsers();
    }

    @Test
    void whenFindByIdThenReturnSuccess() {
        when(userService.findByID(anyInt())).thenReturn(user);
        when(modelMapper.map(any(), any())).thenReturn(userDTO);
        ResponseEntity<UserDTO> response = userResources.findByID(ID);
        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertNotNull(response.getBody());
        assertEquals(UserDTO.class, response.getBody().getClass());
        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(EMAIL, response.getBody().getEmail());
        assertEquals(PASSWORD, response.getBody().getPassword());
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
        user = new Users(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL , PASSWORD);
        usersOptional =  Optional.of(new Users(1, NAME, EMAIL, PASSWORD));
    }
}