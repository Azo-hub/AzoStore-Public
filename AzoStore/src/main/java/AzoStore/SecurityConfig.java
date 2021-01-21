package AzoStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import AzoStore.ModelPackage.SecurityUtility;
import AzoStore.ServicePackage.UserSecurityService;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity (prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private Environment env;
	
	
	@Autowired
	private UserSecurityService userSecurityService;
	
	
	private BCryptPasswordEncoder passwordEncoder () {
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
			"/error"
			
			
	};
	
	
	@Override
	protected void configure (HttpSecurity http) throws Exception {
		
		http
			.authorizeRequests()
			.antMatchers(PUBLIC_MATCHERS)
			.permitAll().anyRequest().authenticated();
		
		http
			.csrf().disable().cors().disable()
			.formLogin().failureUrl("/login?error").defaultSuccessUrl("/")
			.loginPage("/login").permitAll()
			.and()
			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/?logout").deleteCookies("remember-me").permitAll()
			.and()
			.rememberMe();
	}
	
	
	
	@Autowired
	public void configureGlobal (AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService (userSecurityService).passwordEncoder (passwordEncoder());
	}

}
