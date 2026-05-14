package com.alvarohdez.econocom.unit.repositories;

import com.alvarohdez.econocom.enums.UserType;
import com.alvarohdez.econocom.exceptions.EmailAlreadyInUseException;
import com.alvarohdez.econocom.models.User;
import com.alvarohdez.econocom.repositories.FillerUserRepository;
import com.alvarohdez.econocom.unit.utils.constants.UserTestConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("TEST - Filler User Repository")
public class FillerUserRepositoryTest {

    private FillerUserRepository fillerUserRepository;
    private User fakeUser;

    @BeforeEach
    void setUp(){
        fillerUserRepository= new FillerUserRepository();
        fakeUser= new User(UserTestConstants.FAKE_USER_ID,UserTestConstants.FAKE_EMAIL,UserTestConstants.FAKE_ENCODED_PASSWORD,UserType.CLIENT);
    }

    @Test
    @DisplayName("should return the saved user when email is available")
    void shouldReturnSavedUserWhenEmailIsNew(){
        User user= fillerUserRepository.save(fakeUser);
        assertEquals(fakeUser,user);
    }

    @Test
    @DisplayName("should throw exception when email is not available")
    void shouldThrowWhenEmailIsAlreadyInUse(){
        fillerUserRepository.save(fakeUser);
        User user= new User(UserTestConstants.FAKE_USER_ID,UserTestConstants.FAKE_EMAIL,UserTestConstants.FAKE_ENCODED_PASSWORD,UserType.CLIENT);
        assertThrows(EmailAlreadyInUseException.class,
                ()-> fillerUserRepository.save(user));
    }

    @Test
    @DisplayName("should return the user when email exists")
    void shouldReturnUserWhenEmailExists(){
        fillerUserRepository.save(fakeUser);
        Optional<User> user= fillerUserRepository.findUserByEmail(UserTestConstants.FAKE_EMAIL);
        assertAll(
                ()-> assertTrue(user.isPresent()),
                ()-> assertEquals(fakeUser,user.get()));
    }

    @Test
    @DisplayName("should return true when email is not in use")
    void shouldReturnTrueWhenEmailIsNew(){
        assertTrue(fillerUserRepository.isEmailAvailable(UserTestConstants.FAKE_EMAIL));
    }

    @Test
    @DisplayName("should return false when email is already in use")
    void shouldReturnFalseWhenEmailIsAlreadyInUse(){
        fillerUserRepository.save(fakeUser);
        assertFalse(fillerUserRepository.isEmailAvailable(UserTestConstants.FAKE_EMAIL));
    }

}
