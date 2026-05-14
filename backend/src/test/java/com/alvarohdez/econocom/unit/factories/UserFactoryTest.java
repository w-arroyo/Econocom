package com.alvarohdez.econocom.unit.factories;

import com.alvarohdez.econocom.data_safety.FillerPasswordEncoder;
import com.alvarohdez.econocom.enums.UserType;
import com.alvarohdez.econocom.factories.UserFactory;
import com.alvarohdez.econocom.models.User;
import com.alvarohdez.econocom.unit.utils.constants.UserTestConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests - UserFactory")
public class UserFactoryTest {

    @Mock
    private FillerPasswordEncoder fillerPasswordEncoder;

    @InjectMocks
    private UserFactory userFactory;

    @BeforeEach
    void initialize() {
        when(fillerPasswordEncoder.encodePassword(anyString()))
                .thenReturn(UserTestConstants.FAKE_ENCODED_PASSWORD);
    }

    @Test
    @DisplayName("createUserClient: must return CLIENT user")
    void createUserClient_shouldReturnUserWithClientType() {
        User user= userFactory.createUserClient(
                UserTestConstants.FAKE_USER_ID,
                UserTestConstants.FAKE_PLAIN_PASSWORD);
        assertEquals(UserType.CLIENT,user.getUserType());
    }

    @Test
    @DisplayName("createUserClient: id must not be either null or empty")
    void createUserClient_shouldGenerateNonNullId() {
        User user= userFactory.createUserClient(
                UserTestConstants.FAKE_USER_ID,
                UserTestConstants.FAKE_PLAIN_PASSWORD);
        assertNotNull(user.getId());
        assertFalse(user.getId().isEmpty());
    }

    @Test
    @DisplayName("createUserClient: different users different ids")
    void createUserClient_shouldGenerateUniqueIds() {
        User firstUser= userFactory.createUserClient(
                UserTestConstants.FAKE_USER_ID,
                UserTestConstants.FAKE_PLAIN_PASSWORD);
        User secondUser= userFactory.createUserClient(
                UserTestConstants.FAKE_USER_ID,
                UserTestConstants.FAKE_PLAIN_PASSWORD);
        assertNotEquals(firstUser.getId(),secondUser.getId());
    }

    @Test
    @DisplayName("createUserClient: password must be encoded")
    void createUserClient_shouldEncodePassword() {
        User user= userFactory.createUserClient(
                UserTestConstants.FAKE_USER_ID,
                UserTestConstants.FAKE_PLAIN_PASSWORD);
        assertNotNull(user.getEncodedPassword());
        assertNotEquals(UserTestConstants.FAKE_PLAIN_PASSWORD,user.getEncodedPassword());
    }

}
