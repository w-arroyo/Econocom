package com.alvarohdez.econocom.repositories;

import com.alvarohdez.econocom.exceptions.EmailAlreadyInUseException;
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
        User existingUser= database.putIfAbsent(user.getEmail(),user);
        if(existingUser==null){
            return user;
        }
        throw new EmailAlreadyInUseException("Email is already in use.");
    }

    @Override
    public boolean isEmailAvailable(String email) {
        return !database.containsKey(email);
    }


}
