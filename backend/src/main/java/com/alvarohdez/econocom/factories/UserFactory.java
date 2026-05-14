package com.alvarohdez.econocom.factories;

import com.alvarohdez.econocom.data_safety.FillerPasswordEncoder;
import com.alvarohdez.econocom.enums.UserType;
import com.alvarohdez.econocom.models.User;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public final class UserFactory {

    private final FillerPasswordEncoder fillerPasswordEncoder;

    public UserFactory(FillerPasswordEncoder fillerPasswordEncoder) {
        this.fillerPasswordEncoder = fillerPasswordEncoder;
    }

    private User generateUser(String username, String plainTextPassword, UserType userType){
        return new User(
                UUID.randomUUID().toString(),
                username,
                fillerPasswordEncoder.encodePassword(plainTextPassword),
                userType
        );
    }

    public User createUserClient(String username, String plainTextPassword){
        return generateUser(username,plainTextPassword,UserType.CLIENT);
    }

    public User createUserAdmin(String username, String plainTextPassword){
        return generateUser(username,plainTextPassword,UserType.ADMIN);
    }

}
