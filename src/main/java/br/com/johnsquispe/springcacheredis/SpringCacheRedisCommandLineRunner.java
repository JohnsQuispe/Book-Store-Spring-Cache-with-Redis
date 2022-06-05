package br.com.johnsquispe.springcacheredis;

import br.com.johnsquispe.springcacheredis.domain.DomainNotFoundException;
import br.com.johnsquispe.springcacheredis.domain.UserRegisterService;
import br.com.johnsquispe.springcacheredis.domain.UserSearchService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
record SpringCacheRedisCommandLineRunner (UserSearchService userSearchService,
                                          UserRegisterService userRegisterService) implements CommandLineRunner {

    private static final String ADMIN_NAME = "Master";
    private static final String ADMIN_LOGIN = "master";
    private static final String ADMIN_PASSWORD = "masterOfPuppetsImPullingYourString";

    @Override
    public void run(String... args) throws Exception {

        try {
            this.userSearchService.findByLogin(ADMIN_LOGIN);
        } catch (DomainNotFoundException e) {
            this.userRegisterService.createNewUser(ADMIN_NAME, ADMIN_LOGIN, ADMIN_PASSWORD);
        }

    }
}