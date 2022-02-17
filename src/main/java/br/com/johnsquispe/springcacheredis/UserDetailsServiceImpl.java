package br.com.johnsquispe.springcacheredis;

import br.com.johnsquispe.springcacheredis.domain.DomainNotFoundException;
import br.com.johnsquispe.springcacheredis.domain.User;
import br.com.johnsquispe.springcacheredis.domain.UserSearchService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
class UserDetailsServiceImpl implements UserDetailsService {

    private final UserSearchService userSearchService;

    public UserDetailsServiceImpl(UserSearchService userSearchService) {
        this.userSearchService = userSearchService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {

            final User user = this.userSearchService.findByLogin(username);

            return new UserDetailsImpl(user.getSenha(), user.getLogin(), CollectionUtils.emptyCollection());

        } catch (DomainNotFoundException e) {
            throw new UsernameNotFoundException("User Not Found ");
        }

    }
}
