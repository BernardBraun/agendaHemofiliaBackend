package br.com.github.apihemofilia.apihemofilia.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import br.com.github.apihemofilia.apihemofilia.domain.dtos.AuthRequest;
import br.com.github.apihemofilia.apihemofilia.domain.dtos.AuthResponse;
import br.com.github.apihemofilia.apihemofilia.domain.entitys.Login;
import br.com.github.apihemofilia.apihemofilia.infra.TokenService;

@MockitoSettings
@ExtendWith(MockitoExtension.class)
public class AuthenticationControllerTest {

    @Mock
    private AuthenticationManager authManager;

    @Mock
    private TokenService tokenService;

    @InjectMocks
    private AuthenticationController authController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoginWithSuccess() {
    	
        // Mocking input
        String login = "username";
        String password = "password";
        AuthRequest request = new AuthRequest(login, password);

        // Mocking authentication result
        UsernamePasswordAuthenticationToken auth = mock(UsernamePasswordAuthenticationToken.class);
        when(authManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(auth);

        // Mocking the Principal object
        Login loginObject = new Login();
        when(auth.getPrincipal()).thenReturn(loginObject);

        // Mocking token generation
        String token = "generated_token";
        when(tokenService.generatedToken(any(Login.class))).thenReturn(token);

        // Calling the login method
        ResponseEntity<AuthResponse> response = authController.login(request);

        // Verifying the expected behavior
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(token, response.getBody().token());

        // Verifying that the authentication manager and token service were called with the correct arguments
        verify(authManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(tokenService).generatedToken(loginObject);
    }
    
    @Test
    void testLoginWithoutSuccess() {
        // Mocking input
        String login = "username";
        String password = "password";
        AuthRequest request = new AuthRequest(login, password);

        // Mocking authentication failure
        when(authManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Authentication failed."));

        // Calling the login method
        ResponseEntity<AuthResponse> response = authController.login(request);

        // Verifying the expected behavior
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        assertEquals(null, response.getBody()); // Verifying that the response body is null

        // Verifying that the authentication manager and token service were called with the correct arguments
        verify(authManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verifyNoInteractions(tokenService); // Verifying that tokenService was not called
    }   
	
}
