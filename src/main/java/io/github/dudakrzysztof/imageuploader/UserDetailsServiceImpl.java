package io.github.dudakrzysztof.imageuploader;

import io.github.dudakrzysztof.imageuploader.model.AppUser;
import io.github.dudakrzysztof.imageuploader.repo.AppUserRepo;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AppUserRepo repo;

    public UserDetailsServiceImpl(final AppUserRepo repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repo.findByUsername(username);
    }


}
