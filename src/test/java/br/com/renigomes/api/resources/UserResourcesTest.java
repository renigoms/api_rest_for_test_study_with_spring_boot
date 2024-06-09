package br.com.renigomes.api.resources;

import br.com.renigomes.api.Service.impl.UserService;
import br.com.renigomes.api.domain.DTO.UserDTO;
import br.com.renigomes.api.domain.Users;
import org.h2.engine.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserResourcesTest {
    public static final Integer ID = 1;
    public static final String NAME = "Renan Nicolau Gomes";
    public static final String EMAIL = "renan.nic@hotmail.com";
    public static final String PASSWORD = "12345";

    public static final int INDEX = 0;

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
    void whenFindAllThenReturnAListOfUserDTO() {
        when(userService.findAll()).thenReturn(List.of(user));
        when(modelMapper.map(any(), any())).thenReturn(userDTO);
        ResponseEntity<List<UserDTO>> response = userResources.findAll();
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(UserDTO.class, response.getBody().get(INDEX).getClass());
    }

    @Test
    void whenCreateThenReturnCreated() {
         when(userService.create(any())).thenReturn(user);
         ResponseEntity<UserDTO> response = userResources.create(userDTO);
         assertEquals(HttpStatus.CREATED, response.getStatusCode());

    }

    @Test
    void whenUpdateTheReturnSuccess() {
        when(userService.update(userDTO)).thenReturn(user);
        when(modelMapper.map(any(), any())).thenReturn(userDTO);
        ResponseEntity<UserDTO> response = userResources.update(ID, userDTO);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserDTO.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(EMAIL, response.getBody().getEmail());
    }

    @Test
    void whenDeleteThenReturnSuccess() {
        doNothing().when(userService).delete(anyInt());
        ResponseEntity<UserDTO> response = userResources.delete(ID);
        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        verify(userService, times(1)).delete(anyInt());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    private void startUsers(){
        user = new Users(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL , PASSWORD);
        usersOptional =  Optional.of(new Users(1, NAME, EMAIL, PASSWORD));
    }
}