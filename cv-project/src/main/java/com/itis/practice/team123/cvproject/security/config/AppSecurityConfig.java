package com.itis.practice.team123.cvproject.security.config;

import com.itis.practice.team123.cvproject.security.filters.JwtAuthenticationFilter;
import com.itis.practice.team123.cvproject.security.providers.JwtAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
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
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CharacterEncodingFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig {
    @Order(2)
    @Configuration
    @RequiredArgsConstructor
    public static class SessionConfig extends WebSecurityConfigurerAdapter {
        private final PasswordEncoder passwordEncoder;
        private final UserDetailsService userDetailsService;

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
    @RequiredArgsConstructor
    public static class JwtConfig extends WebSecurityConfigurerAdapter {
        private final JwtAuthenticationProvider authenticationProvider;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/api/**").csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            http.anonymous().principal("guest").authorities("GUEST_ROLE");
            http.formLogin().disable();
            http.logout().disable();
            http.addFilterAt(tokenProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
            http.addFilterBefore(new AnonymousAuthenticationFilter("twrt454"), JwtAuthenticationFilter.class);
            //http.addFilterBefore(characterEncodingFilter(), AnonymousAuthenticationFilter.class);
        }


        @Bean
        public JwtAuthenticationFilter tokenProcessingFilter() throws Exception {
            JwtAuthenticationFilter tokenProcessingFilter = new JwtAuthenticationFilter();
            tokenProcessingFilter.setAuthenticationManager(authenticationManager());
            return tokenProcessingFilter;
        }


        @Autowired
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(authenticationProvider).authenticationProvider(new AnonymousAuthenticationProvider("twrt454"));
        }


    }
}