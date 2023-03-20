package edu.betty.JXIpet.config;

import edu.betty.JXIpet.security.PersonDetails;
import edu.betty.JXIpet.services.PeopleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(personDetailsService);
//        authenticationProvider.setPasswordEncoder(passwordEncoder);
//        return authenticationProvider;
//    }

//    @Bean
//    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
//        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder.authenticationProvider(authenticationProvider());
//
//        return authenticationManagerBuilder.build();
//    }
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
//        UserDetails user = User.withUsername("user")
//                .password(passwordEncoder.encode("password"))
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.withUsername("admin")
//                .password(passwordEncoder.encode("admin"))
//                .roles("USER", "ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable().authorizeRequests().
//                antMatchers("/auth/login","/error","/auth/registration","/people/test")
//                .permitAll().anyRequest().authenticated().and().
//                formLogin().loginPage("/auth/login").
//                loginProcessingUrl("/process_login")
//                .defaultSuccessUrl("/people",true)
//                .failureUrl("/auth/login?error").and().logout()
//                .logoutUrl("/logout").logoutSuccessUrl("/auth/login");
//        return http.build();
//    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        return encoder;
//    }
//    $2a$1 0$c5M M5BvY ohZVH mMid/ wPu.C WbWQy AVR9N zt1OD A2yZ1 SytUD Htnri
//    $2a$10$c5MM5BvYohZVHmMid/wPu.CWbWQyAVR9Nzt1ODA2yZ1SytUDHtnri
//    $2a$10$c5MM5BvYohZVHmMid/wPu.CWbWQyAVR9Nzt1ODA2yZ1SytUDHtnri
    private final PeopleDetailService personDetailsService;
    @Autowired
    public SecurityConfig(PeopleDetailService personDetailsService){
        this.personDetailsService=personDetailsService;
    }


    @Override
    protected void configure(HttpSecurity http)throws Exception{
        http.authorizeRequests().
                antMatchers("/admin").hasRole("ADMIN").
                antMatchers("/api/v1/developers","/auth/login","/error","/auth/registration","/people/test")
                .permitAll().anyRequest().hasAnyRole("USER","ADMIN").and().
                formLogin().loginPage("/auth/login").
                loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/people",true)
                .failureUrl("/auth/login?error").and().logout()
                .logoutUrl("/logout").logoutSuccessUrl("/auth/login");
    }
//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService(){
//        return new JdbcUserDetailsManager((DataSource) User.builder().username("admin").password("admin").roles("admin").build());
//
//    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personDetailsService).passwordEncoder(getPasswordEncoder());//NoOpPasswordEncoder.getInstance());

    }
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
