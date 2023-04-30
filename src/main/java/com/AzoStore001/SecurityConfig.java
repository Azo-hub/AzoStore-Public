package com.AzoStore001;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.AzoStore001.CustomHandler.CustomLoginFailureHandler;
import com.AzoStore001.CustomHandler.CustomLoginSuccessHandler;
import com.AzoStore001.Model.SecurityUtility;
import com.AzoStore001.Service.UserSecurityService;
import com.AzoStore001.oauth2.CustomOAuth2UserService;
import com.AzoStore001.oauth2.OAuth2LoginSuccessHandler;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity (prePostEnabled = true)
public class SecurityConfig{
	@Autowired
	private Environment env;
	
	@Autowired
	private DataSource dataSource;

	
	@Autowired
	private UserSecurityService userSecurityService;
	
	

	private BCryptPasswordEncoder passwordEncoder () {
		return SecurityUtility.passwordEncoder();
	}

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

			"/error",
			"/oauth2/**"
			
			
	};
	
	
	@Bean
	SecurityFilterChain SecurityFilterChain (HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(requests -> 
                	requests
                        .antMatchers(PUBLIC_MATCHERS)
                        .permitAll().anyRequest().authenticated());
        http
                .csrf(csrf -> 
                	csrf.disable())
                .cors(cors -> 
                	cors.disable())
                .formLogin(login -> 
                	login.defaultSuccessUrl("/")
                         .loginPage("/login").permitAll()
                         .failureHandler(loginFailureHandler)
                         .successHandler(loginSuccessHandler))
                .oauth2Login(login -> 
                	login
                         .loginPage("/login")
                         .userInfoEndpoint().userService(oAuth2UserService)
                         .and()
                         .successHandler(oAuth2LoginSuccessHandler))
                .logout(logout -> 
                	logout
                		 .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                         .logoutSuccessUrl("/?logout") /*.deleteCookies("remember-me") */ .permitAll())
                .rememberMe(me -> me
                        .tokenValiditySeconds(3 * 24 * 60 * 60)
                        .tokenRepository(persistentTokenRepository()));
        
        return http.build();
	
	}
	
	
	@Bean
	PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
		return tokenRepository;
	}
	
	
	
	@Autowired
	public void configureGlobal (AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService (userSecurityService).passwordEncoder (passwordEncoder());
	}
	
	
	@Autowired
	private CustomLoginFailureHandler loginFailureHandler;
	
	@Autowired
	private CustomLoginSuccessHandler loginSuccessHandler;
	
	@Autowired
	private CustomOAuth2UserService oAuth2UserService;
	
	@Autowired
	private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;


			
}


    