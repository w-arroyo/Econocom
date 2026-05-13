package com.alvarohdez.econocom.unit.models;

import com.alvarohdez.econocom.enums.UserType;
import com.alvarohdez.econocom.models.User;
import com.alvarohdez.econocom.unit.utils.constants.UserTestConstants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests - User")
public class UserTest {

    @Nested // this organizes tests in a group since in this case they're all related (related meaning same constructor)
    @DisplayName("no-args constructor")
    class NoArgsConstructorTest {

        @Test
        @DisplayName("should create a User instance successfully")
        void shouldCreateUserInstance() {
            User user= new User();
            assertNotNull(user);
        }

        @Test
        @DisplayName("all fields should be null after no-args construction")
        void allFieldsShouldBeNullByDefault() {
            User user= new User();
            assertAll("all fields must be null",
                    () -> assertNull(user.getId()),
                    () -> assertNull(user.getEmail()),
                    () -> assertNull(user.getEncodedPassword()),
                    () -> assertNull(user.getUserType()));
        }
    }

    @Nested
    @DisplayName("all-args constructor")
    class AllArgsConstructorTest {

        @Test
        @DisplayName("all fields should contain value")
        void shouldAssignAllFields() {
            User user= new User(
                    UserTestConstants.FAKE_USER_ID,
                    UserTestConstants.FAKE_EMAIL,
                    UserTestConstants.FAKE_ENCODED_PASSWORD,
                    UserType.CLIENT);
            assertAll("all fields have value",
                    () -> assertEquals(UserTestConstants.FAKE_USER_ID,user.getId()),
                    () -> assertEquals(UserTestConstants.FAKE_EMAIL,user.getEmail()),
                    () -> assertEquals(UserTestConstants.FAKE_ENCODED_PASSWORD,user.getEncodedPassword()),
                    () -> assertEquals(UserType.CLIENT,user.getUserType()));
        }
    }

    @Nested
    @DisplayName("Getters and Setters")
    class GettersAndSettersTest {

        @Test
        @DisplayName("setEmail and getEmail should work correctly")
        void emailGetterAndSetterShouldWork() {
            User user= new User();
            user.setEmail(UserTestConstants.FAKE_EMAIL);
            assertEquals(UserTestConstants.FAKE_EMAIL,user.getEmail());
        }

        @Test
        @DisplayName("setEncodedPassword and getEncodedPassword should work correctly")
        void encodedPasswordGetterAndSetterShouldWork() {
            User user= new User();
            user.setEncodedPassword(UserTestConstants.FAKE_ENCODED_PASSWORD);
            assertEquals(UserTestConstants.FAKE_ENCODED_PASSWORD,user.getEncodedPassword());
        }

        @Test
        @DisplayName("setUserType and getUserType should work correctly")
        void userTypeGetterAndSetterShouldWork() {
            User user= new User();
            user.setUserType(UserType.CLIENT);
            assertEquals(UserType.CLIENT,user.getUserType());
        }
    }

}
