package md.electron.electronbackend.service.security;

import md.electron.electronbackend.persistence.model.User;
import md.electron.electronbackend.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(final String userName) throws UsernameNotFoundException
    {
        final User dbUser = repository.findByName(userName);

        return new org.springframework.security.core.userdetails.User(userName, dbUser.getPassword(),
                true, true, true, true,
                AuthorityUtils.createAuthorityList(dbUser.getRole()));
    }
}
