package vita.bloom.front.end.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    // Dentro de WebSecurityConfig
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
            .csrf().disable() // Necessário para permitir acesso ao POST
            .httpBasic(Customizer.withDefaults())
            .authorizeHttpRequests(
                authorize -> authorize.requestMatchers(HttpMethod.DELETE, "/vitabloom/deletar/{id}").permitAll() //Relato
                                      .requestMatchers(HttpMethod.GET, "/vitabloom/produtos").permitAll()
                                      .requestMatchers(HttpMethod.DELETE, "/vitabloom/carrinho/deletar/{id}").permitAll() //Relato
                                      .requestMatchers(HttpMethod.GET, "/vitabloom/carrinho").permitAll()
                                      .requestMatchers(HttpMethod.POST, "/carrinho/adicionar-itemm").permitAll()
                                      .requestMatchers(HttpMethod.POST, "/carrinho/adicionar-item").permitAll()
                                      ) 
            .authorizeHttpRequests(
                authorize -> authorize.requestMatchers(HttpMethod.POST, "/vitabloom/produto/inserir").authenticated()
                .requestMatchers(HttpMethod.POST, "/vitabloom/carrinho/inserir").authenticated()
                                    );

        return http.build();
    }

    // Dentro de WebSecurityConfig
    @Bean
    public UserDetailsService userDetailsService() {
         UserDetails user = User.withDefaultPasswordEncoder()
             .username("connectdengue")
             .password("connectdengue")
             .build();

         return new InMemoryUserDetailsManager(user);
     }
}