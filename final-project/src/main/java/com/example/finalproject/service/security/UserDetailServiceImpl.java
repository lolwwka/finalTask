package com.example.finalproject.service.security;

import com.example.finalproject.entity.Role;
import com.example.finalproject.entity.User;
import com.example.finalproject.repository.VisitorRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private final VisitorRepository visitorRepository;

    public UserDetailServiceImpl(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = visitorRepository.findByEmail(username);
        org.springframework.security.core.userdetails.User.UserBuilder builder = null;
        if (user != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            if (user.getAuthenticated()) {
                builder.password(user.getPassword());
            }
            builder.roles(user.getRoles().stream().map(Role::getName).toArray(size -> new String[size]));
        } else {
            throw new UsernameNotFoundException("User not found");
        }
        return builder.build();
    }
}
