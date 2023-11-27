package com.example.pdvapp.security;

import com.example.pdvapp.dto.LoginDTO;
import com.example.pdvapp.entity.User;
import com.example.pdvapp.exception.PasswordNotFoundException;
import com.example.pdvapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException("Login Invalido");
        }

        return new UserPrincipal(user);
    }

    public void verifyUserCredentials(LoginDTO login){

        UserDetails user = loadUserByUsername(login.getUsername());
        boolean passwordIsTheSame = SecurityConfig.passwordEncoder()
                .matches(login.getPassword(), user.getPassword());

        if(!passwordIsTheSame){
            throw new PasswordNotFoundException("Senha Invalida");
        }
    }
}
