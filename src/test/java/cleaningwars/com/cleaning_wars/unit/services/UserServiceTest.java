package cleaningwars.com.cleaning_wars.unit.services;

import cleaningwars.com.cleaning_wars.entities.Home;
import cleaningwars.com.cleaning_wars.entities.User;
import cleaningwars.com.cleaning_wars.repositories.UserRepository;
import cleaningwars.com.cleaning_wars.security.PasswordEncoder;
import cleaningwars.com.cleaning_wars.services.implementations.UserServiceImplementation;
import cleaningwars.com.cleaning_wars.services.interfaces.HomeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private HomeService homeService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImplementation userService;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

    }

    @Test
    void testCreateUser() {

        User user = new User();
        user.setPassword("rawPassword");

        when(passwordEncoder.encodePassword("rawPassword")).thenReturn("encodedPassword");
        when(homeService.createHome(user)).thenReturn(new Home());
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.createUser(user);

        assertNotNull(result);
        assertEquals("encodedPassword", result.getPassword());
        verify(userRepository, times(1)).save(user);

    }

    @Test
    void testGetUserById() {

        User user = new User();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.getUserById(1L);

        assertNotNull(result);
        assertEquals(user, result);

    }

    @Test
    void testGetUserByUsername() {

        User user = new User();
        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));

        User result = userService.getUserByUsername("testUser");

        assertNotNull(result);
        assertEquals(user, result);

    }

    @Test
    void testUpdateUser() {

        // Missing

    }

    @Test
    void testDeleteUser() {

        userService.deleteUser(1L);
        verify(userRepository, times(1)).deleteById(1L);

    }

    @Test
    void testUserNotFound() {

        // Missing

    }
}