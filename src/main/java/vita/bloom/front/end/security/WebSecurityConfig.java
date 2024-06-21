package vita.bloom.front.end.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import vita.bloom.front.end.security.service.AdminDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    // Dentro de WebSecurityConfig.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
            .csrf().disable() // Necessário para permitir acesso ao POST
            .httpBasic(Customizer.withDefaults())
            .authorizeHttpRequests(
                authorize -> authorize
                        // URLs Produtos.
                        .requestMatchers(HttpMethod.GET, "/vitabloom/produtos").permitAll()
                        .requestMatchers(HttpMethod.GET, "/vitabloom/produto/{id}").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/vitabloom/produto/editar/{id}").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/vitabloom/deletar/{id}").permitAll()
                        // URLs Carrinho.
                        .requestMatchers(HttpMethod.GET, "/vitabloom/carrinho").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/vitabloom/carrinho/deletar/{id}").permitAll()
                        // URLs Usuários.
                        .requestMatchers(HttpMethod.GET, "/vitabloom/usuarios/verificar/{email}/{senha}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/vitabloom/usuarios/verificar/email/{email}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/vitabloom/usuarios/listarid/{idUsuario}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/vitabloom/usuarios/listar").permitAll()
                        .requestMatchers(HttpMethod.POST, "/vitabloom/usuarios/inserir").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/vitabloom/usuario/editar/{id}").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/vitabloom/usuario/adicionaritem/{id}").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/vitabloom/usuario/removeitem/{idUsuario}/{idItem}").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/vitabloom/usuario/delete/{id}").permitAll()

                        .requestMatchers(HttpMethod.POST, "/admin/criar").permitAll()
                                      )
            .authorizeHttpRequests(
                authorize -> authorize
                    .requestMatchers(HttpMethod.POST, "/vitabloom/produto/inserir").authenticated()
                    .requestMatchers(HttpMethod.POST, "/vitabloom/carrinho/inserir").authenticated()
                    .requestMatchers(HttpMethod.GET, "/admin/listar").authenticated()
                                    );
        return http.build();
    }

    // Dentro de WebSecurityConfig para segurança .
    @Bean
    public UserDetailsService userDetailsService() {
        return new AdminDetailsService();
     }

     @Bean
     public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
     }

}
