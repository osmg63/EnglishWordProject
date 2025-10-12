package com.example.english.service;

import com.example.english.dto.DtoMapper;
import com.example.english.dto.DtoUser;
import com.example.english.dto.DtoUserIU;
import com.example.english.entity.Role;
import com.example.english.entity.User;
import com.example.english.repo.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.reactivestreams.Publisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;


    @Mock
    private DtoMapper dtoMapper;
    public UserServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUser() {
        // Mock DTO
        DtoUserIU dtoUserIU = new DtoUserIU();
        dtoUserIU.setPassword("plainPassword");
        dtoUserIU.setUsername("osman");

        // Mock Entity
        User user = new User(1,"os","gm","as","123",2,true,true,true,true,Collections.singleton(Role.ROLE_USER));


        User savedUser = new User(1,"os","gm","as","123",2,true,true,true,true,Collections.singleton(Role.ROLE_USER));


        // Mock Dto dönüşümleri
        DtoUser dtoUser = new DtoUser();
        dtoUser.setId(1);
        dtoUser.setUsername("osman");

        when( dtoMapper.userRequestDtoToUser(dtoUserIU)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(savedUser);
        when( dtoMapper.userToUserResponseDto(savedUser)).thenReturn(dtoUser);

        // Test edilen metod
        DtoUser result = userService.save(dtoUserIU);

        // Doğrulamalar
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("osman", result.getUsername());

        verify(userRepository, times(1)).save(user);
    }



}
