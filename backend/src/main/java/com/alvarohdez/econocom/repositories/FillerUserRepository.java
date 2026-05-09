package com.alvarohdez.econocom.repositories;

import com.alvarohdez.econocom.models.User;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class FillerUserRepository implements UserRepository {

    private final Map<String,User> database;

    public FillerUserRepository() {
        this.database=new ConcurrentHashMap<>();
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return Optional.ofNullable(database.get(email));
    }

    @Override
    public User save(User user) {
        database.put(user.getEmail(),user);
        return database.get(user.getEmail());
    }


}
