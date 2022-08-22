package org.sid.securite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()                                                        //disable Cross-Site Request Forgery CSRF
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //no session will be created or used by Spring Security
        .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/categories/**").permitAll()
                .antMatchers(HttpMethod.GET,"/sousCategories/**").permitAll()
                .antMatchers(HttpMethod.GET,"/annonces/**").permitAll()
                .antMatchers(HttpMethod.GET,"/utilisateurs/**").permitAll()
                .antMatchers(HttpMethod.GET,"/gestionAbonnes/**","/gestionAnnonces/**","/signalAnnonces/**").permitAll()
                .antMatchers("/login/**","/inscription/**").permitAll();

        http.authorizeRequests().antMatchers("/recherche/**").permitAll();

        http.authorizeRequests().antMatchers("/commenter/**","/saveAnnonce/**","/signalerAnnonce/**").hasAuthority("ABONNE");
        http.authorizeRequests().antMatchers("/supprimerAnnonce/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers("/bloquerAbonne/**","/debloquerAbonne/**","/supprimerAbonne/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers("/gestionAnnonces/**","/gestionAbonnes/**").hasAuthority("SuperAdmin");
        http.authorizeRequests().antMatchers("/addSubCategory/**","/ajouterAdmin/**","/gestionAbonnes/**").hasAuthority("SuperAdmin");

        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
        http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);


    }
}
