package tn.esprit.brogram.backend.security;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustumUserDetailService custuUserDetailService;

    @Bean
    public SecurityFilterChain appSecurityFilterChain(HttpSecurity http) throws Exception{
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.cors().disable()
            .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().formLogin().disable()
                .securityMatcher("/**")
                .authorizeHttpRequests(Registry -> Registry

                        .requestMatchers("/").permitAll()
                        .requestMatchers("/UniversiteRestController/**").permitAll()
                        .requestMatchers("/FoyerRestController/**").permitAll()
                        .requestMatchers("/sendMail/**").permitAll()
                        .requestMatchers("/DemandeRestController/**").permitAll()
                        .requestMatchers("/ChamberRestController/**").permitAll()
                        .requestMatchers("/ReservationRestController/**").permitAll()
                        .requestMatchers("/BlocRestController/**").permitAll()

                        .requestMatchers("/UserRestController/**").permitAll()
                        .requestMatchers("/auth/login").permitAll()
                                                .requestMatchers("/verify").permitAll()
                        .requestMatchers("/reset-password/**").permitAll()
                        .requestMatchers("/auth/register").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception{
        return  http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(custuUserDetailService)
                .passwordEncoder(passwordEncoder())
                .and().build();
    }

}
