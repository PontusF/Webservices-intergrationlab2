package com.example.gatewayzuul;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//@Author: Pontus Fredriksson
@EnableWebSecurity
public class SecurityTokenConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private com.example.gatewayzuul.JwtConfig jwtConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                //Cross-site request forgery
                .csrf().disable()
                //session won't be used to store user's state.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterAfter(new JwtTokenAuthenticationFilter(jwtConfig), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, jwtConfig.getUri()).permitAll()
                .antMatchers("/say" + "/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated();
    }
    @Bean
    public JwtConfig jwtConfig(){
        return new JwtConfig();
    }


}
