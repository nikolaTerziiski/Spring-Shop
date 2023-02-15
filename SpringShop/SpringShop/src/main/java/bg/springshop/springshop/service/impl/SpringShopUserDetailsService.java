package bg.springshop.springshop.service.impl;

import bg.springshop.springshop.model.entity.User;
import bg.springshop.springshop.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class
SpringShopUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public SpringShopUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userEntity = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User with username" + username + "is not found."));

        return map(userEntity);

    }
    private UserDetails map(User user) {
        Set<GrantedAuthority> grantedAuthorities =
            user.
                getRoles().
                stream().
                map(r -> new SimpleGrantedAuthority("ROLE_" + r.getName().name())).
                collect(Collectors.toUnmodifiableSet());

        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            grantedAuthorities
        );
    }
}
