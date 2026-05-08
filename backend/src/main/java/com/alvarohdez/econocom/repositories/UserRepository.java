package com.alvarohdez.econocom.repositories;

import com.alvarohdez.econocom.models.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByUserName(String username);

    void save(User user);

}
