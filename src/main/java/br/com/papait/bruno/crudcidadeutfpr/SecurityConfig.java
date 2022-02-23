package br.com.papait.bruno.crudcidadeutfpr;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
            .inMemoryAuthentication()
            .withUser("brunopapait")
            .password(passwordEncoder().encode("araruna123"))
            .authorities("listar")
            .and()
            .withUser("brunop").password(passwordEncoder().encode("araruna456"))
            .authorities("admin");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .csrf()
            .disable()
            .authorizeRequests()
            .antMatchers("/")
            .hasAnyAuthority("admin", "listar")
            .antMatchers("/criar")
            .hasAnyAuthority("admin")
            .antMatchers("/excluir")
            .hasAnyAuthority("admin")
            .antMatchers("preparaAlterar")
            .hasAnyAuthority("admin")
            .antMatchers("/alterar")
            .hasAnyAuthority("admin")
            .anyRequest()
            .denyAll()
            .and()
            .formLogin()
            .permitAll();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
