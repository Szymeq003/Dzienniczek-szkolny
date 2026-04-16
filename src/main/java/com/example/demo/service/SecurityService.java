package com.example.demo.service;

import com.example.demo.model.AppUser;
import com.example.demo.model.Teacher;
import com.example.demo.model.Student;
import com.example.demo.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final AppUserRepository appUserRepository;

    public Optional<AppUser> getCurrentAppUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            return Optional.empty();
        }

        String username;
        if (authentication.getPrincipal() instanceof UserDetails) {
            username = ((UserDetails) authentication.getPrincipal()).getUsername();
        } else {
            username = authentication.getPrincipal().toString();
        }

        return appUserRepository.findByUsername(username);
    }

    public Optional<Teacher> getCurrentTeacher() {
        return getCurrentAppUser().map(AppUser::getTeacher);
    }

    public Optional<Student> getCurrentStudent() {
        return getCurrentAppUser().map(AppUser::getStudent);
    }
}
