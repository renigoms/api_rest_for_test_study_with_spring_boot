package br.com.renigomes.api.resources.exeptions;

import br.com.renigomes.api.Service.exceptions.DataInterativeViolationException;
import br.com.renigomes.api.Service.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ResoucesExceptionHandlerTest {
    public static final String USERS_NOT_FOUND = "Users not found!";
    public static final String E_MAIL_JA_CADASTRADO_NO_SISTEMA = "E-mail já cadastrado no sistema !";

    @InjectMocks
    private ResoucesExceptionHandler resoucesExceptionHandler;

    @BeforeEach
    void setUp() {
    }

    @Test
    void whenObjectNotFoundExceptionThenReturnAResponseEntity() {
        ResponseEntity<StandardErrorExeption> response = resoucesExceptionHandler
                .objectNotFound(new ObjectNotFoundException(USERS_NOT_FOUND),
                        new MockHttpServletRequest());
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardErrorExeption.class, response.getBody().getClass());
        assertEquals(USERS_NOT_FOUND, response.getBody().getError());
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getBody().getStatus());
    }

    @Test
    void dataInterativeViolationException() {
        ResponseEntity<StandardErrorExeption> response = resoucesExceptionHandler
                .dataIntegrityveViolationException(
                        new DataInterativeViolationException(E_MAIL_JA_CADASTRADO_NO_SISTEMA),
                        new MockHttpServletRequest());
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardErrorExeption.class, response.getBody().getClass());
        assertEquals(E_MAIL_JA_CADASTRADO_NO_SISTEMA, response.getBody().getError());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getBody().getStatus());
    }
}