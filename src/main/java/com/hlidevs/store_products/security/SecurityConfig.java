package com.hlidevs.store_products.security;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();}

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConf) throws Exception {
        return authConf.getAuthenticationManager();
    }


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic(Customizer.withDefaults());

        http.logout(logout -> logout
                .logoutUrl("/perform_logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")

        );

        /*
        http.logout(logout -> logout
                .logoutUrl("/logout") // URL de logout personalizada
                .logoutSuccessUrl("/") // URL a la que redirigir después de hacer logout
                .invalidateHttpSession(true) // Invalidar la sesión HTTP después del logout
                .deleteCookies("JSESSIONID")); // Eliminar las cookies después del logout

         */


        http.authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(HttpMethod.GET, "/products/all").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/doc/swagger-ui/index.html").hasRole("ADMIN")
                        .anyRequest().permitAll());

        http.csrf(AbstractHttpConfigurer::disable);

        //http.exceptionHandling().accessDeniedPage("/src/main/resources/templates/error.html");
        return http.build();

    }

    /*
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails roberto = User.builder()
                .username("roberto")
                .password(passwordEncoder().encode("123456"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(roberto, admin);
    }

     */

}
