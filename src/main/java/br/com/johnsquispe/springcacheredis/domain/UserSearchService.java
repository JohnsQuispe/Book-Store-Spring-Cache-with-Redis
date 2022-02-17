package br.com.johnsquispe.springcacheredis.domain;

import org.springframework.stereotype.Service;

@Service
public class UserSearchService {

    private final UserRepository userRepository;

    public UserSearchService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByLogin (String login) throws DomainNotFoundException {
        return this.userRepository.findByLogin(login).orElseThrow(DomainNotFoundException::new);
    }

}
