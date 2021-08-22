package com.stackhack.timesheet.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.servlet.http.HttpServletResponse;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) ->
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
        .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/api/v2/customers/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/oauth/**").permitAll()
                .antMatchers(HttpMethod.GET, "/saml/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/internal/v2/**").permitAll()
                .antMatchers("/**").permitAll()
                .and()
                .httpBasic();
    }



}
