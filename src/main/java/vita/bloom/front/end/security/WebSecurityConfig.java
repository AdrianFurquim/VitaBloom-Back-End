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
    // Dentro de WebSecurityConfig.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
            .csrf().disable() // Necessário para permitir acesso ao POST
            .httpBasic(Customizer.withDefaults())
            .authorizeHttpRequests(
                authorize -> authorize.requestMatchers(HttpMethod.DELETE, "/vitabloom/deletar/{id}").permitAll()
                                        // URLs Produtos.
                                      .requestMatchers(HttpMethod.GET, "/vitabloom/produtos").permitAll()
                                      .requestMatchers(HttpMethod.PUT, "/vitabloom/produto/editar/{id}").permitAll()
                                        // URLs Carrinho.
                                      .requestMatchers(HttpMethod.DELETE, "/vitabloom/carrinho/deletar/{id}").permitAll()
                                      .requestMatchers(HttpMethod.GET, "/vitabloom/carrinho").permitAll()
                                      .requestMatchers(HttpMethod.GET, "/vitabloom/carrinho/ver").permitAll()
                                      .requestMatchers(HttpMethod.PUT, "/carrinho/modificar/{id}").permitAll()
                                        // URLs Carrinho Service.
                                      .requestMatchers(HttpMethod.DELETE, "/carrinho/item/delete/{id}").permitAll()
                                      .requestMatchers(HttpMethod.POST, "/carrinho/adicionar-itemm").permitAll()
                                      .requestMatchers(HttpMethod.POST, "/carrinho/adicionar-item").permitAll()
                                      .requestMatchers(HttpMethod.POST, "/carrinho/remover-item").permitAll()
                                        // URLs Usuários.
                                      .requestMatchers(HttpMethod.POST, "/vitabloom/usuarios/inserir").permitAll()
                                      .requestMatchers(HttpMethod.GET, "/vitabloom/usuarios/listar").permitAll()
                                      .requestMatchers(HttpMethod.DELETE, "/vitabloom/usuario/delete/{id}").permitAll()
                                      .requestMatchers(HttpMethod.PUT, "/vitabloom/usuario/editar/{id}").permitAll()
                                      .requestMatchers(HttpMethod.PUT, "/vitabloom/usuario/adicionaritem/{id}").permitAll()
                                      .requestMatchers(HttpMethod.PUT, "/vitabloom/usuario/removeitem/{idUsuario}/{idItem}").permitAll()
                                      .requestMatchers(HttpMethod.GET, "/vitabloom/usuarios/verificar/{email}/{senha}").permitAll()
                                      .requestMatchers(HttpMethod.GET, "/vitabloom/usuarios/listarid/{idUsuario}").permitAll()

                                      ) 
            .authorizeHttpRequests(
                authorize -> authorize.requestMatchers(HttpMethod.POST, "/vitabloom/produto/inserir").authenticated()
                .requestMatchers(HttpMethod.POST, "/vitabloom/carrinho/inserir").authenticated()
                                    );

        return http.build();
    }

    // Dentro de WebSecurityConfig para segurança .
    @Bean
    public UserDetailsService userDetailsService() {
         UserDetails user = User.withDefaultPasswordEncoder()
             .username("VitaBloom")
             .password("VitaBloom123")
             .build();

         return new InMemoryUserDetailsManager(user);
     }
}