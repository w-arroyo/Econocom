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
    public Optional<User> findByUserName(String username) {
        return Optional.ofNullable(database.get(username));
    }

    @Override
    public void save(User user) {
        database.put(user.getUserName(),user);
    }


}
