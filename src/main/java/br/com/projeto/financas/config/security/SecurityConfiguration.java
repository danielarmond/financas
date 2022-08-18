package br.com.projeto.financas.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.projeto.financas.repository.UsuarioRepository;

@Configuration
public class SecurityConfiguration {

	@Autowired
	private AutenticacaoService autenticacaoService;
	
	@Autowired
    private TokenService tokenService;
	
	@Autowired
    private UsuarioRepository usuarioRepository;
	
		
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
	
	@Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
	
   @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers(HttpMethod.GET, "/despesas").permitAll()
        .antMatchers(HttpMethod.GET, "/despesas/*").permitAll()
        .antMatchers(HttpMethod.POST, "/despesas").hasRole("ANALISTA")
        .antMatchers(HttpMethod.PUT, "/despesas/*").hasRole("ANALISTA")
        .antMatchers(HttpMethod.DELETE, "/despesas/*").hasRole("ANALISTA")
        
        .antMatchers(HttpMethod.GET, "/receitas").permitAll()
        .antMatchers(HttpMethod.GET, "/receitas/*").permitAll()
        .antMatchers(HttpMethod.POST, "/receitas").hasRole("ANALISTA")
        .antMatchers(HttpMethod.PUT, "/receitas/*").hasRole("ANALISTA")
        .antMatchers(HttpMethod.DELETE, "/receitas/*").hasRole("ANALISTA")

        .antMatchers(HttpMethod.POST, "/auth").permitAll()
        .anyRequest().authenticated()
        .and().csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
    }

	
}
