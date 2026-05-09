package com.alvarohdez.econocom.initializer;

import com.alvarohdez.econocom.app_config.AppConfig;
import com.alvarohdez.econocom.factories.UserFactory;
import com.alvarohdez.econocom.repositories.FillerUserRepository;
import com.alvarohdez.econocom.repositories.UserRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public final class DatabaseInitializer {

    private final FillerUserRepository userRepository;
    private final UserFactory userFactory;

    public DatabaseInitializer(FillerUserRepository userRepository, UserFactory userFactory) {
        this.userRepository = userRepository;
        this.userFactory = userFactory;
    }

    @PostConstruct
    public void init(){
        userRepository.save(
                userFactory.createUserClient(AppConfig.getFillerUserEmail(),AppConfig.getFillerUserPassword()));
    }

}
