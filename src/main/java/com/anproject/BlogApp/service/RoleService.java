package com.anproject.BlogApp.service;

import com.anproject.BlogApp.entity.Role;
import org.springframework.stereotype.Service;
import com.anproject.BlogApp.repository.RoleRepository;

import java.util.List;

@Service
public class RoleService {

    private  RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

}
