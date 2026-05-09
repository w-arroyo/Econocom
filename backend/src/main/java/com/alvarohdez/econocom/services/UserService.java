package com.alvarohdez.econocom.services;

import com.alvarohdez.econocom.models.User;
import com.alvarohdez.econocom.repositories.FillerUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final FillerUserRepository fillerUserRepository;

    public UserService(FillerUserRepository fillerUserRepository) {
        this.fillerUserRepository = fillerUserRepository;
    }

    public User saveUser(User userToSave){
        return fillerUserRepository.save(userToSave);
    }

}
