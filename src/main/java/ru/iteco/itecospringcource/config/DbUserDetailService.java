package ru.iteco.itecospringcource.config;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.iteco.itecospringcource.model.entity.UserAuthEntity;
import ru.iteco.itecospringcource.repository.UserAuthRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DbUserDetailService implements UserDetailsService {

    private final UserAuthRepository userAuthRepository;

    public DbUserDetailService(UserAuthRepository userAuthRepository) {
        this.userAuthRepository = userAuthRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuthEntity user = userAuthRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<SimpleGrantedAuthority> authorityList = user.getRoles().stream().map(roleEntity -> new SimpleGrantedAuthority(roleEntity.getName()))
                .collect(Collectors.toList());
        return new User(user.getUsername(), user.getPassword(), authorityList);
    }
}
