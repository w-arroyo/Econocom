package com.alvarohdez.econocom.security;

import com.alvarohdez.econocom.models.User;
import com.alvarohdez.econocom.repositories.FillerUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class FillerUserDetailsService implements UserDetailsService {

    private final FillerUserRepository fillerUserRepository;

    public FillerUserDetailsService(FillerUserRepository fillerUserRepository) {
        this.fillerUserRepository = fillerUserRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User foundUser=fillerUserRepository.findUserByEmail(username)
                .orElseThrow(()->new UsernameNotFoundException("Email does not exist in the database."));
        return new org.springframework.security.core.userdetails.User(
                foundUser.getId(),
                foundUser.getEncodedPassword(),
                Collections.emptyList() // i got no roles to handle so
        );
    }
}
