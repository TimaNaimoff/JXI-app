package edu.betty.JXIpet.config;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.WebMvcSecurityConfiguration;
import org.springframework.security.config.authentication.AuthenticationManagerFactoryBean;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final AuthProvider authProvider;
    @Autowired
    public SecurityConfig(AuthProvider authProvider){
        this.authProvider=authProvider;
    }
    //Настраивает аутентификацию

    protected void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(authProvider);

    }
}
