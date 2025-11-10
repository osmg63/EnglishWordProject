package com.example.english.service;

import com.example.english.entity.Role;
import com.example.english.entity.User;
import com.example.english.repo.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
@Service
public class OAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public OAuth2UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User delegate=super.loadUser(userRequest);
        Map<String,Object> attrs=delegate.getAttributes();


        String email=attrs.get("email").toString();
        String name = attrs.getOrDefault("name",email).toString();


        User user= userRepository.findByUsername(email)
                .orElseGet(()->{
                    User user1=new User();
                    user1.setUsername(email);
                    user1.setPassword(passwordEncoder.encode(email));
                    user1.setName(name);
                    user1.setAuthorities(Collections.singleton(Role.ROLE_USER));
                    user1.setAccountNonExpired(true);
                    user1.setAccountNonLocked(true);
                    user1.setCredentialsNonExpired(true);
                    user1.setEnabled(true);
                    return userRepository.save(user1);
                });

        return new DefaultOAuth2User(
                Set.of(()->user.getAuthorities().toString()),
                attrs,
                "email"
        );

    }






}
