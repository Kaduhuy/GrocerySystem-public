package com.example.grocery_system.service;

import com.example.grocery_system.model.Users;
import com.example.grocery_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(String name, String email, String rawPassword) {
        if (userRepository.findByEmail(email).isPresent()) {
<<<<<<< HEAD
            throw new RuntimeException("User email already exists: " + email);
=======
            throw new RuntimeException("User already exists");
>>>>>>> aea768050c8b39c347dfeba62a25b6b8079d851e
        }

        Users user = new Users();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRole("USER");

        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<Users> user = userRepository.findByEmail(name);
        if (user.isPresent()) {
            var userObj = user.get();
            return User.builder()
                    .username(userObj.getName())
                    .password(userObj.getPassword())
                    .build();
        }else{
            throw new UsernameNotFoundException(name);
        }
    }
}
