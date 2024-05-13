package com.epitech.pictsmanager.service.jwt;

import com.epitech.pictsmanager.entity.User;
import com.epitech.pictsmanager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Service implementation for loading user details used in JWT authentication
 * @author Jean-Baptiste, Kamel, Victor, Mahdi
 */
@Service
public class UserServiceImp implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Loads user details by username (email) for authentication
     * @param email The email (username) of the user
     * @return UserDetails containing user details
     * @throws UsernameNotFoundException If the user is not found
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email " + email));
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),Collections.emptyList());

    }
}
