//package com.stockmateapp.userprofileservice.serviceImpl;
//
//import com.stockmateapp.userprofileservice.model.User;
//import com.stockmateapp.userprofileservice.repository.UserProfileRepository;
//import com.stockmateapp.userprofileservice.serviceImpl.KafkaServicempl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import java.util.ArrayList;
//import java.util.Optional;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class UserServiceImplTest {
//
//    @InjectMocks
//    private UserServiceImpl userService;
//
//    @Mock
//    private UserProfileRepository userProfileRepository;
//
//    @InjectMocks
//    private KafkaServicempl kafkaService;
//
//    private User user;
//
//    @BeforeEach
//    public void setUp() {
//        user = new User(1L, "Test", "User", "testuser@email.com", "testuser", "testpassword");
//    }
//
//    @Test
//    public void registerUserTest() {
//        when(userProfileRepository.findByUsername(user.getUsername())).thenReturn(null);
//        when(userProfileRepository.findByEmail(user.getEmail())).thenReturn(null);
//        when(userProfileRepository.save(user)).thenReturn(user);
//
//        assertEquals(user, userService.registerUser(user));
//        verify(userProfileRepository, times(1)).findByUsername(user.getUsername());
//        verify(userProfileRepository, times(1)).findByEmail(user.getEmail());
//        verify(userProfileRepository, times(1)).save(user);
//    }
//
//    @Test
//    public void getAllUsersTest() {
//        ArrayList<User> users = new ArrayList<>();
//        users.add(user);
//
//        when(userProfileRepository.findAll()).thenReturn(users);
//
//        assertEquals(users, userService.getAllUsers());
//        verify(userProfileRepository, times(1)).findAll();
//    }
//
//    @Test
//    public void getUserByIdTest() {
//        when(userProfileRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
//
//        assertEquals(user, userService.getUserById(user.getUserId()));
//        verify(userProfileRepository, times(1)).findById(user.getUserId());
//    }
//
//    @Test
//    public void getUserByUsernameTest() {
//        when(userProfileRepository.findByUsername(user.getUsername())).thenReturn(user);
//
//        assertEquals(user, userService.getUserByUsername(user.getUsername()));
//        verify(userProfileRepository, times(1)).findByUsername(user.getUsername());
//    }
//
//    @Test
//    public void getUserByEmailTest() {
//        when(userProfileRepository.findByEmail(user.getEmail())).thenReturn(user);
//
//        assertEquals(user, userService.getUserByEmail(user.getEmail()));
//        verify(userProfileRepository, times(1)).findByEmail(user.getEmail());
//    }
//
//    @Test
//    public void updateUserTest() {
//        when(userProfileRepository.findByUsername(user.getUsername())).thenReturn(user);
//        when(userProfileRepository.save(user)).thenReturn(user);
//
//        assertEquals(user, userService.updateUser(user, user.getUsername()));
//        verify(userProfileRepository, times(1)).findByUsername(user.getUsername());
//        verify(userProfileRepository, times(1)).save(user);
//    }
//
//    @Test
//    public void deleteUserTest() {
//        when(userProfileRepository.findByUsername(user.getUsername())).thenReturn(null);
//        when(userProfileRepository.findByEmail(user.getEmail())).thenReturn(null);
//        when(userProfileRepository.save(user)).thenReturn(user);
//        userService.registerUser(user);
//        doNothing().when(userProfileRepository).deleteById(user.getUserId());
//
//        assertEquals("User Deleted", userService.deleteUser(user.getUsername()));
//        verify(userProfileRepository, times(1)).deleteById(user.getUserId());
//    }
//}
