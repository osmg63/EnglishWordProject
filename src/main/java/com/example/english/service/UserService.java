package com.example.english.service;

import com.example.english.dto.DtoMapper;
import com.example.english.dto.DtoUser;
import com.example.english.dto.DtoUserIU;
import com.example.english.entity.Role;
import com.example.english.entity.User;
import com.example.english.exception.BaseException;
import com.example.english.exception.ErrorMessage;
import com.example.english.exception.MessageType;
import com.example.english.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final DtoMapper dtoMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByUsername(username);
        return user.orElseThrow(EntityNotFoundException::new);
    }

    public List<DtoUser> findAll(){
        try{
            return dtoMapper.usersToUserResponseDto(userRepository.findAll());
        }catch (Exception e){
            throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION,e.getMessage()));
        }
    }
    public void deleteById(int id) {
        try{
            userRepository.deleteById(id);
        }catch (Exception e){
            throw new BaseException(new ErrorMessage(MessageType.DElETE_FAILED));
        }

    }
    public DtoUser save(DtoUserIU dto) {
        try{
            User user =dtoMapper.userRequestDtoToUser(dto);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setAccountNonExpired(true);
            user.setAccountNonLocked(true);
            user.setCredentialsNonExpired(true);
            user.setEnabled(true);
            user.setAuthorities(Collections.singleton(Role.ROLE_USER));
            User savedUser=userRepository.save(user);
            return dtoMapper.userToUserResponseDto(savedUser);
        }catch (Exception e){
            throw new BaseException(new ErrorMessage(MessageType.RECORD_FAILED,e.getMessage()));
        }
    }

    public DtoUser checkCredentials(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return dtoMapper.userToUserResponseDto(user);
            }
        }
        throw new BaseException(new ErrorMessage(MessageType.INVALID_PASSWORD_OR_USERNAME));
    }
    public void addPoint(int id){
        try{
            User user=userRepository.findById(id);
            System.out.println(user.getPoint());
            user.setPoint(user.getPoint()+1);

            userRepository.save(user);

        }catch (Exception e){
            throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION,e.getMessage()));
        }

    }
    public String pointShow(int id){
        User user = userRepository.findById(id);
        if (user==null) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_FOUND));
        }
        return String.valueOf(user.getPoint());
    }

    public DtoUser getUserById(int id) {
        User data= userRepository.findById(id);
        if (data==null) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_FOUND));
        }
        return dtoMapper.userToUserResponseDto(data);
    }
    public DtoUser updateUser(int id, DtoUser dto) {
        try{
            User data= userRepository.findById(id);
            if (data==null) {
                throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_FOUND));
            }
            dtoMapper.updateUserFromDto(dto,data);
            return dtoMapper.userToUserResponseDto(userRepository.save(data));
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION));
        }

    }


    public DtoUser getUserByUserName(String userName) {
        try{
            Optional<User> data=userRepository.findByUsername(userName);
            if (data.isEmpty()) {
                throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_FOUND));
            }
            return dtoMapper.userToUserResponseDto(data.get());
        } catch (BaseException e) {
            log.error(e.getMessage());
            throw e;
        }catch (Exception e) {
            log.error(e.getMessage());
            throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION));
        }
    }
}
