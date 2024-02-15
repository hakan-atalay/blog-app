package com.anproject.BlogApp.service;

import com.anproject.BlogApp.entity.User;
import com.anproject.BlogApp.payload.request.RegularUserRequestDto;
import com.anproject.BlogApp.repository.RoleRepository;
import com.anproject.BlogApp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

     private final PasswordEncoder passwordEncoder;
     private final UserRepository userRepository;
     private final RoleRepository roleRepository;

     public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
          this.passwordEncoder = passwordEncoder;
          this.userRepository = userRepository;
          this.roleRepository = roleRepository;
     }

     public void save(RegularUserRequestDto regularUserRequestDto){
          User user = RegularUserRequestDto.mapRequestDtoToEntity(regularUserRequestDto);
          user.setRole(roleRepository.findRoleByRoleName("USER").orElseThrow());
          user.setPassword(passwordEncoder.encode(regularUserRequestDto.getPassword()));
          user.setStatus(true);
          userRepository.save(user);
     }

}
