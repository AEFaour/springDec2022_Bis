package biblio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity	
	public class MultiHttpSecurityConfig  {
	
	public MultiHttpSecurityConfig() {
		System.out.println("**** MultiHttpSecurityConfig ****");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder () { 
	    return new BCryptPasswordEncoder(); 
	
	}
	
	
	@Bean    
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder)  {
        // ensure the passwords are encoded properly
		UserBuilder user = User.builder(); 
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(user
        		.username("user")
        		.password(passwordEncoder.encode("password"))
        		.roles("USER","API").build());
        manager.createUser(user
        		.username("a")
        		.password(passwordEncoder.encode("1"))
        		.roles("API","ADMIN")
        		.build());
        return manager;
    }
	
	@Bean
	@Order(2)
	public SecurityFilterChain SecurityFilterChain1(HttpSecurity http) throws Exception {
		http
	    	.authorizeRequests()
	    		.antMatchers("/spring/jsp/home").authenticated()
	    		.antMatchers("/spring/jsp/livres/**").hasAnyRole("USER", "ADMIN")
	    		.antMatchers("/spring/jsp/adherents/**", "/spring/jsp/emprunts/**").hasRole("ADMIN")
	    .and()
		.formLogin()	
		.and()
		.logout().deleteCookies("JSESSIONID").logoutSuccessUrl("/login")
		.and()
	      .exceptionHandling().accessDeniedPage("/WEB-INF/jsp/accessDenied.jsp")
		.and()
	    .rememberMe().disable();
		return http.build();
	}  
    
	
	@Bean
	@Order(1)
	public SecurityFilterChain SecurityFilterChain2(HttpSecurity http) throws Exception {
		http
       .csrf().disable()
       .formLogin().disable()
       .antMatcher("/spring/rest/**")
       	.authorizeRequests()
       			.antMatchers(HttpMethod.POST, "/spring/rest/**").hasRole("ADMIN")
       			.antMatchers(HttpMethod.PUT, "/spring/rest/**").hasRole("ADMIN")
       			.antMatchers(HttpMethod.DELETE, "/spring/rest/**").hasRole("ADMIN")
       			.antMatchers(HttpMethod.GET, "/spring/rest/**").hasRole("API")
		 .and()
			.httpBasic();
		return http.build();
       
   }
  
}

