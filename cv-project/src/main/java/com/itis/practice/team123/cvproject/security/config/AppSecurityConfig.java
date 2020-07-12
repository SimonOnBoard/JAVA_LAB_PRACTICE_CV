package com.itis.practice.team123.cvproject.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AnonymousAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig {
    @Order(2)
    @Configuration
    public static class SessionConfig extends WebSecurityConfigurerAdapter {
        private PasswordEncoder passwordEncoder;
        private UserDetailsService userDetailsService;

        public SessionConfig(PasswordEncoder passwordEncoder, @Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
            this.passwordEncoder = passwordEncoder;
            this.userDetailsService = userDetailsService;
        }

        @Autowired
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/**");
            http.anonymous().principal("guest").authorities("GUEST_ROLE");
            http.formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/profile", true)
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .failureUrl("/login?error=true");

            http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .deleteCookies("JSESSIONID")
                    .invalidateHttpSession(true)
                    .logoutSuccessUrl("/login?logout");
        }


        @Autowired
        public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        }
    }

    @Order(1)
    @Configuration
    public static class JwtConfig extends WebSecurityConfigurerAdapter {


        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/api/**").csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            http.anonymous().principal("guest").authorities("GUEST_ROLE");
            http.formLogin().disable();
            http.logout().disable();
            http.addFilterBefore(new AnonymousAuthenticationFilter("twrt454"), UsernamePasswordAuthenticationFilter.class);
        }


        @Autowired
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(new AnonymousAuthenticationProvider("twrt454"));
        }

    }
}