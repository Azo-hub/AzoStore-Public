package com.AzoStore001;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.server.authentication.logout.DelegatingServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.SecurityContextServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.WebSessionServerLogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.AzoStore001.Model.SecurityUtility;
import com.AzoStore001.Service.UserSecurityService;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	@Autowired
	private Environment env;
	
	
	@Autowired
	private UserSecurityService userSecurityService;
	
	
	private BCryptPasswordEncoder bCryptPasswordEncoder() {
        return SecurityUtility.passwordEncoder();
    }
	
	
	
	
	private static final String[] PUBLIC_MATCHERS = {
			"/css/**",
			"/js/**",
			"/Images/**",
			"/myaccount",
			"/",
			"/searchByCategory",
			"/searchProduct",
			"/bootstrapcss/**",
			"/css/bootstrap.min.css",
			"/css/jquery.dataTables.min.css",
			"/bootstrapjs/**",
			"/newUser",
			"/forgetPassword",
			"/updateUserInfo",
			"/customjs/**",
			"/login",
			"/fonts/**",
			"/bootstrapcss/bootstrap.css",
			"/datatable/**",
			"/bootbox/**",
			"/customcss/**",
			"/fontawesome/**",
			"/bootstrapjs/bootstrap.min.js", 
			"/jquery-3.4.1.slim.min.js", 
			"/popper.min.js", 
			"/jquery-3.4.1.min.js",
			"/viewproductdetail/{id}",
			"/product/**",
			"/datatable/js/datatables.min.js",
			"/viewproductdetails/**",
			"/error"
			
			
	};


    
	@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception  {
		
		AuthenticationManagerBuilder authenticationManagerBuilder = http
                .getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder.userDetailsService(userSecurityService).passwordEncoder(bCryptPasswordEncoder());
       
		
		http
			.authorizeHttpRequests(auth -> {
				auth.requestMatchers(PUBLIC_MATCHERS).permitAll();
				auth.anyRequest().authenticated();
			});


         http
         	.csrf(csrf -> csrf.disable())
         	.cors(cors -> cors.disable())
         	.formLogin(form -> 
         		form
         			.failureUrl("/login?error").defaultSuccessUrl("/")
     				.loginPage("/login")
     				.permitAll())
         	
         	.logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/?logout").deleteCookies("remember-me").permitAll())
            .rememberMe(withDefaults());
         
         return http.build();
		
	}
	
	
	@Bean
    AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
	
	
	
	

}
