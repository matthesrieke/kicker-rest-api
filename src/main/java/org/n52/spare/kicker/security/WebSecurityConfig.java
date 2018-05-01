package org.n52.spare.kicker.security;

import org.n52.spare.kicker.repositories.PlayerRepository;
import org.springframework.beans.factory.InitializingBean;
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
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements InitializingBean {
	
	@Autowired
	private PlayerRepository playerRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .httpBasic().and()
            .authorizeRequests()
                .antMatchers("/", "/matches").permitAll()
                .anyRequest().authenticated()
            .and().csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            .and()
            	.addFilterBefore(new WebMvcCorsFilter(), ChannelProcessingFilter.class);
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return new RepositoryUserDetailsManager(playerRepo, passwordEncoder);
    }
    
    @Bean(name = "passwordEncoder")
    public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
    
	@Override
	public void afterPropertiesSet() throws Exception {
		insertDummyData();
	}
	
	public void insertDummyData() {
//		Player p1 = new Player();
//		p1.setNickName("Mathijsen");
//		p1.setFirstName("Matthes");
//		p1.setLastName("Rieke");
//		p1.setPassword(passwordEncoder.encode("asdf"));
//		
//		Player p2 = new Player();
//		p2.setNickName("Staschinho");
//		p2.setFirstName("Christoph");
//		p2.setLastName("Stasch");
//		p2.setPassword(passwordEncoder.encode("asdf2"));
//		
//		playerRepo.save(p1);
//		playerRepo.save(p2);
	}
    
}