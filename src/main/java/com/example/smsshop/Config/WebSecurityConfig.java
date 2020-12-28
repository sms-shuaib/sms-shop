package com.example.smsshop.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {



	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private UserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Override
	public void configure(WebSecurity web) throws Exception {
		// Filters will not get executed for the resources
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// configure AuthenticationManager so that it knows from where to load
		// user for matching credentials
		// Use BCryptPasswordEncoder
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}



	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// We don't need CSRF for this example
		//httpSecurity.cors();

		// Add a filter to validate the tokens with every request
		//httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

		//		httpSecurity.cors().and().csrf().disable()
		//		.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class).
		//		authorizeRequests().antMatchers("/getAll","/category","/getId").permitAll().anyRequest().authenticated().and().sessionManagement()
		//		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		httpSecurity.csrf().disable().cors().and().authorizeRequests().antMatchers("api/**")
		.authenticated().antMatchers("api/authenticate").permitAll()
		.antMatchers("**").permitAll()
		.and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

		// Add a filter to validate the tokens with every request
		//	httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

		/** 
		 httpSecurity.cors().and()
				// dont authenticate this particular request
				.authorizeRequests().antMatchers("/authenticate").permitAll().antMatchers("/getAll").permitAll().antMatchers(HttpMethod.OPTIONS, "/**")
				.permitAll().
				// all other requests need to be authenticated
						anyRequest().authenticated().and().
				// make sure we use stateless session; session won't be used to
				// store user's state.
						exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// Add a filter to validate the tokens with every request
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		 **/
	}

	//	@Bean
	//    public CorsConfigurationSource corsConfigurationSource() {
	//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	//        CorsConfiguration configuration = new CorsConfiguration();
	//        configuration.setAllowedOrigins(Arrays.asList("*"));
	//        configuration.setAllowedMethods(Arrays.asList("GET","POST","DELETE", "PUT", "PATCH" ,"OPTIONS"));
	//        configuration.setAllowedHeaders(Arrays.asList("Authorization", "content-type","Access-Control-Allow-Origin","Access-Control-Allow-Credentials"));
	//        configuration.setExposedHeaders(Arrays.asList("Authorization", "content-type","Access-Control-Allow-Origin","Access-Control-Allow-Credentials"));
	//      //  configuration.setAllowCredentials(true);
	//        source.registerCorsConfiguration("/**", configuration);
	//        return source;
	//    }
}
