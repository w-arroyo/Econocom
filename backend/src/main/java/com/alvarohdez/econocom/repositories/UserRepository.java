package com.alvarohdez.econocom.repositories;

import com.alvarohdez.econocom.models.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findUserByEmail(String email);

    User save(User user);

}
