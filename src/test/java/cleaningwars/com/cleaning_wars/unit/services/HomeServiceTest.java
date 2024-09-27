package cleaningwars.com.cleaning_wars.unit.services;

import cleaningwars.com.cleaning_wars.entities.Home;
import cleaningwars.com.cleaning_wars.entities.User;
import cleaningwars.com.cleaning_wars.factories.HomeFactory;
import cleaningwars.com.cleaning_wars.repositories.HomeRepository;
import cleaningwars.com.cleaning_wars.repositories.UserRepository;
import cleaningwars.com.cleaning_wars.services.implementations.HomeServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HomeServiceTest {

    @Mock
    private HomeRepository homeRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private HomeFactory homeFactory;

    @InjectMocks
    private HomeServiceImplementation homeService;

    private User testUser;
    private Home testHome;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testUser = new User();
        testUser.setId(1L);

        testHome = new Home();
        testHome.setId(1L);
        testHome.setUsers(new HashSet<>(Collections.singletonList(testUser)));
    }

    @Test
    void testCreateHome() {
        when(homeFactory.createDefaultHome(testUser)).thenReturn(testHome);

        Home result = homeService.createHome(testUser);

        assertNotNull(result);
        assertEquals(testHome, result);
        verify(homeFactory, times(1)).createDefaultHome(testUser);
    }

    @Test
    void testGetHomeById() {
        when(homeRepository.findById(1L)).thenReturn(Optional.of(testHome));

        Home result = homeService.getHomeById(1L);

        assertNotNull(result);
        assertEquals(testHome, result);
    }

    @Test
    void testGetAllHomes() {
        when(homeRepository.findAll()).thenReturn(Collections.singletonList(testHome));

        var result = homeService.getAllHomes();

        assertEquals(1, result.size());
    }

    @Test
    void testAddUserToHome() {
        when(homeRepository.findById(1L)).thenReturn(Optional.of(testHome));

        homeService.addUserToHome(1L, testUser);

        assertTrue(testHome.getUsers().contains(testUser));
        verify(userRepository, times(1)).save(testUser);
        verify(homeRepository, times(1)).save(testHome);
    }

    @Test
    void testRemoveUserFromHome() {
        when(homeRepository.findById(1L)).thenReturn(Optional.of(testHome));
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));

        homeService.removeUserFromHome(1L, 1L);

        assertFalse(testHome.getUsers().contains(testUser));
        verify(userRepository, times(1)).save(testUser);
        verify(homeRepository, times(1)).save(testHome);
    }

    @Test
    void testDeleteHome() {
        homeService.deleteHome(1L);
        verify(homeRepository, times(1)).deleteById(1L);
    }
}