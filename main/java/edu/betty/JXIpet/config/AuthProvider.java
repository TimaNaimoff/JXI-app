package edu.betty.JXIpet.config;

import edu.betty.JXIpet.security.PersonDetails;
import edu.betty.JXIpet.services.PeopleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class AuthProvider implements AuthenticationProvider {
    @Autowired
    private PeopleDetailService personDetails;

    public AuthProvider(){
        super();
    }
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName= authentication.getName();
        UserDetails personDetailss=personDetails.loadUserByUsername(userName);
        String password=authentication.getCredentials().toString();
        if(!password.equals(personDetailss.getPassword())){
            throw new BadCredentialsException("Incorrect login or password!");
          }
        return new UsernamePasswordAuthenticationToken(personDetailss,password,
                Collections.emptyList());

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
