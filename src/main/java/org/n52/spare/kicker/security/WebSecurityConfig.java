package org.n52.spare.kicker.security;

import org.n52.spare.kicker.WebMvcCorsFilter;
import org.n52.spare.kicker.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private PlayerRepository playerRepo;
	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .httpBasic().and()
            .authorizeRequests()
                .antMatchers("/", "/matches", "/login").permitAll()
                .anyRequest().authenticated()
            .and()
            	.addFilterBefore(new WebMvcCorsFilter(), ChannelProcessingFilter.class);
    }

//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        return new RepositoryUserDetailsManager(playerRepo, passwordEncoder);
//    }
    
//    @Bean(name = "passwordEncoder")
//    public PasswordEncoder passwordEncoder() {
//    	return new BCryptPasswordEncoder();
//    }
    
    
}