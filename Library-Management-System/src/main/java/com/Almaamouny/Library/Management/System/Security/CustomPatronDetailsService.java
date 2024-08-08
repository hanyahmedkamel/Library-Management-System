package com.Almaamouny.Library.Management.System.Security;


import com.Almaamouny.Library.Management.System.Entities.Patron;
import com.Almaamouny.Library.Management.System.Repositories.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomPatronDetailsService implements UserDetailsService {

    @Autowired
    private PatronRepository patronRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Patron patron = patronRepository.findByUsername(username);
        if (patron == null) {
            throw new UsernameNotFoundException("Patron not found with username: " + username);
        }
        return new User(
                patron.getUsername(),
                patron.getPassword(),
                AuthorityUtils.commaSeparatedStringToAuthorityList(patron.getRoles())
        );
    }
}