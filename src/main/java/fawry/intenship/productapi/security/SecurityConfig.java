package fawry.intenship.productapi.security;

import fawry.intenship.productapi.security.appUser.AppUserService;
import fawry.intenship.productapi.security.filter.CustomAuthenticationFilter;
import fawry.intenship.productapi.security.filter.CustomAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration

public class SecurityConfig extends WebSecurityConfigurerAdapter  {

    private final String [] public_endpoints={
                "/login" , "/registration","/role","/role/addtouser"
            ,"/product","/category","/orders/**"
    };
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                http.authorizeRequests().antMatchers("/login/**").permitAll();
                http.authorizeRequests().antMatchers(HttpMethod.POST,"/registration/**").permitAll();
                http.authorizeRequests().antMatchers(HttpMethod.GET,"/product/**").hasAnyAuthority("ROLE_USER");
                http.authorizeRequests().antMatchers(HttpMethod.POST,"/product/**").hasAnyAuthority("ROLE_ADMIN");
                http.authorizeRequests().antMatchers(HttpMethod.GET,"/category/**").hasAnyAuthority("ROLE_USER");
                http.authorizeRequests().antMatchers(HttpMethod.POST,"/category/**").hasAnyAuthority("ROLE_ADMIN");
                http.authorizeRequests().antMatchers(HttpMethod.GET,"/order/**").hasAnyAuthority("ROLE_USER");
                http.authorizeRequests().antMatchers(HttpMethod.POST,"/order/**").hasAnyAuthority("ROLE_USER");
                http.addFilter(new CustomAuthenticationFilter(authenticationManager()));
                http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
        auth.userDetailsService(appUserService).passwordEncoder(bCryptPasswordEncoder);

    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){

        DaoAuthenticationProvider provider=
                new DaoAuthenticationProvider();

        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(appUserService);

        return provider;
    }
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}

