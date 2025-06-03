package br.com.fiap.sentinel_api.service;

import br.com.fiap.sentinel_api.entity.User;
import br.com.fiap.sentinel_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Tentando carregar usuário com email: " + username);

        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> {
                    System.out.println("Usuário não encontrado: " + username);
                    return new UsernameNotFoundException("User not found");
                });

//        System.out.println("Usuário encontrado: " + user.getEmail());
//        System.out.println("Senha (hash) do usuário: " + user.getPassword());
//        System.out.println("Role do usuário: " + user.getRole().name());

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }
}
