package br.com.johnsquispe.springcacheredis;

import br.com.johnsquispe.springcacheredis.domain.DomainNotFoundException;
import br.com.johnsquispe.springcacheredis.domain.User;
import br.com.johnsquispe.springcacheredis.domain.UserSearchService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public record UserDetailsServiceImpl (UserSearchService userSearchService) implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {

            final User user = this.userSearchService.findByLoginCached(username);

            return new UserDetail(user.getPassword(), user.getLogin(), CollectionUtils.emptyCollection());

        } catch (DomainNotFoundException e) {
            throw new UsernameNotFoundException("User Not Found ");
        }

    }
}
