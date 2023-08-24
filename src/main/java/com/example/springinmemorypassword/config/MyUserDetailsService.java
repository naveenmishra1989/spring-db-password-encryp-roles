package com.example.springinmemorypassword.config;

import com.example.springinmemorypassword.entity.User;
import com.example.springinmemorypassword.entity.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        final Optional<User> userOptional = userRepository.findByUsername(username);
        userOptional.orElseThrow(() -> new UsernameNotFoundException("Not Found Exception"));
        return userOptional.map(CustomUserDetails::new).get();
    }

    public String userName() {
        final Authentication authentication = SecurityContextHolder.getContext()
            .getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken))
        return authentication.getName();
        return null;
    }

}
