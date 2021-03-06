package xyz.innky.graduationproject.web.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.io.PrintWriter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/**/*.img").permitAll()
                .mvcMatchers("/common/image/file").permitAll()
                .mvcMatchers("/**/*file").permitAll()
                .mvcMatchers("/user/registerStudent").permitAll()
                .mvcMatchers("/user/**").permitAll()
                .mvcMatchers("/user/registerTeacher").permitAll()
                .mvcMatchers("/admin/**").hasRole("administrator")
                .mvcMatchers("/student/**").hasRole("student")
                .mvcMatchers("/teacher/**").hasRole("teacher")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/login")
                .successHandler((req, res, auth) -> {
                    res.setContentType("application/json;charset=UTF-8");
                    res.getWriter().write("{\"code\":200,\"message\":\"????????????\", \"data\":{\"token\":\"" + auth.getName() + "\"}}");
                })
                .failureHandler((req, res, exp) -> {
                    res.setContentType("application/json;charset=UTF-8");
                    res.getWriter().write("{\"code\":500,\"message\":\"????????????\", \"data\":\"" + exp.getMessage() + "\"}");
                })
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler((request, response, authentication) -> {
                    response.setContentType("application/json;charset=UTF-8");
                    response.getWriter().write("{\"code\":200,\"message\":\"????????????\"}");
                })
                .and()
                .csrf().disable()
                .cors()
                .and()
                .exceptionHandling().authenticationEntryPoint((httpServletRequest, httpServletResponse, e) -> {
                    httpServletResponse.setContentType("application/json;charset=utf-8");
                    PrintWriter writer = httpServletResponse.getWriter();
                    writer.write("{\"code\":500,\"message\":\"?????????\"}");
                });
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOriginPattern("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }
}
