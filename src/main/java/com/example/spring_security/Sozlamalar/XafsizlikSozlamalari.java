package com.example.spring_security.Sozlamalar;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class XafsizlikSozlamalari extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
//                .antMatchers(HttpMethod.POST,"/userAPI/postUser/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET,"/userAPI/get/**").hasAnyRole("ADMIN","SUPERUSER")
//                .antMatchers(HttpMethod.GET,"/userAPI/getby/*").hasAnyRole("ADMIN","SUPERUSER","USER")
//                .antMatchers(HttpMethod.DELETE,"/userAPI/delet/*").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("Derektor").password(passwordEncoder().encode("123")).roles("ADMIN")
//                .and()
//                .withUser("admin").password(passwordEncoder().encode("111")).roles("SUPERUSER")
//                .and()
//                .withUser("foydalanuvchi").password(passwordEncoder().encode("222")).roles("USER");
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("123")).roles("ADMIN").authorities("ADDUSER","DELETEUSER","UPDATEUSER","GETUSER","GETUSERBYID")
                .and()
                .withUser("superadmin").password(passwordEncoder().encode("111")).roles("SUPERUSER ").authorities("UPDATEUSER","GETUSER","GETUSERBYID")
                .and()
                .withUser("operator").password(passwordEncoder().encode("222")).roles("OPERATOR").authorities("GETUSERBYID");
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
