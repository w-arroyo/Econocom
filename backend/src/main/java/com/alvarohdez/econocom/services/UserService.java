package com.alvarohdez.econocom.services;

import com.alvarohdez.econocom.dto.RegistrationRequestDTO;
import com.alvarohdez.econocom.exceptions.EmailAlreadyInUseException;
import com.alvarohdez.econocom.factories.UserFactory;
import com.alvarohdez.econocom.models.User;
import com.alvarohdez.econocom.repositories.FillerUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final FillerUserRepository fillerUserRepository;
    private final UserFactory userFactory;

    public UserService(FillerUserRepository fillerUserRepository, UserFactory userFactory) {
        this.fillerUserRepository = fillerUserRepository;
        this.userFactory = userFactory;
    }

    public User saveClientUser(RegistrationRequestDTO registrationRequestDTO){
        if(fillerUserRepository.isEmailAvailable(registrationRequestDTO.getEmail())){
            User userToSave= userFactory.createUserClient(registrationRequestDTO.getEmail(),registrationRequestDTO.getPassword());
            return fillerUserRepository.save(userToSave);
        }
        throw new EmailAlreadyInUseException("Email is already in use.");
    }

}
