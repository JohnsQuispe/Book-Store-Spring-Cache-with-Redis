package br.com.johnsquispe.springcacheredis.domain;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public record UserRegisterService (PasswordEncoder passwordEncoder,
                            UserRepository userRepository) {

    public User createNewUser (String name, String login, String password) {

        final String encryptedPassword = this.passwordEncoder.encode(password);

        final User user = new User(name, login, encryptedPassword);

        return this.userRepository.save(user);

    }

}
