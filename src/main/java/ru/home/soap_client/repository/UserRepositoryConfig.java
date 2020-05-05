package ru.home.soap_client.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

/**
 * Created by Denis Maltsev on 05.05.2020.
 */
@Configuration
public class UserRepositoryConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("ru.home.springsoap.entity");
        return marshaller;
    }

    @Bean
    public UserRepository userRepository(Jaxb2Marshaller marshaller) {
        UserRepository userRepository = new UserRepository();
        userRepository.setDefaultUri("http://localhost:8080/ws");
        userRepository.setMarshaller(marshaller);
        userRepository.setUnmarshaller(marshaller);

        return userRepository;
    }
}
