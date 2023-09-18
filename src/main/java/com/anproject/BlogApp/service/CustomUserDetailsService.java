package com.anproject.BlogApp.service;

import com.anproject.BlogApp.entity.CustomUserDetail;
import com.anproject.BlogApp.entity.Role;
import com.anproject.BlogApp.entity.User;
import com.anproject.BlogApp.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        User user = userRepository.getUserByNickname(nickname);

        if (user == null) {
            throw new UsernameNotFoundException("Kullanıcı bulunamadı.");
        }

        return new CustomUserDetail(user);
    }

}
