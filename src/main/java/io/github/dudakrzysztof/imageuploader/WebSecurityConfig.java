package io.github.dudakrzysztof.imageuploader;

import io.github.dudakrzysztof.imageuploader.model.AppUser;
import io.github.dudakrzysztof.imageuploader.repo.AppUserRepo;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsService;
    private AppUserRepo repo;

    public WebSecurityConfig(final UserDetailsServiceImpl userDetailsService, final AppUserRepo repo) {
        this.userDetailsService = userDetailsService;
        this.repo = repo;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/test1").hasRole("USER")
                .antMatchers("/test2").hasRole("ADMIN")
                .and()
                .formLogin().permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void get(){
        AppUser appUserUser = new AppUser("UserJan",
                passwordEncoder().encode("Jan123"), "ROLE_USER");
        AppUser appUserAdmin = new AppUser("AdminJan",
                passwordEncoder().encode("Jan123"), "ROLE_ADMIN");
        repo.save(appUserUser);
        repo.save(appUserAdmin);
    }
}
