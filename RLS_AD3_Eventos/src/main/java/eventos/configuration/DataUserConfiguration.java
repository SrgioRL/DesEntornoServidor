package eventos.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class DataUserConfiguration {

	
	/**
	 * Crea y configura un UserDetailsManager con consultas personalizadas para la autenticación.
	 * Utiliza JdbcUserDetailsManager y configura consultas para buscar usuarios y sus roles
	 * en la base de datos. Establece consultas para obtener detalles del usuario 
	 * basándose en el nombre de usuario.
	 * 
	 * @param dataSource La fuente de datos para las operaciones de base de datos.
	 * @return Una instancia configurada de UserDetailsManager.
	 */
	@Bean
	public UserDetailsManager usersCustom(DataSource dataSource) {

		JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
		users.setUsersByUsernameQuery("select username,password,enabled from Usuarios u where username=?");
		users.setAuthoritiesByUsernameQuery("select u.username,p.nombre from Usuario_Perfiles up "
				+ "inner join usuarios u on u.username = up.username "
				+ "inner join perfiles p on p.id_perfil = up.id_perfil " + "where u.username = ?");

		return users;

	}

	
	/**
	 * Configura la cadena de filtros de seguridad para la aplicación, definiendo reglas de autorización para distintas rutas.
	 * Permite el acceso sin autenticación a recursos estáticos y a ciertas rutas como la página de inicio,
	 * registro, login y logout. Además, especifica rutas accesibles únicamente por usuarios con el rol de CLIENTE y requiere
	 * autenticación para todas las demás solicitudes. Configura la página de login personalizada, la página a la que redirigir
	 * en caso de fallo en el login y la URL de éxito de logout.
	 * 
	 * @param http Objeto HttpSecurity para configurar las reglas de seguridad.
	 * @return La cadena de filtros de seguridad configurada.
	 * @throws Exception Si ocurre un error durante la configuración de la seguridad.
	 */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.csrf(csrf -> csrf.disable())
	            .authorizeHttpRequests(authorize -> authorize
	                    .requestMatchers("/static/**", "/css/**", "/js/**", "/json/**", "/img/**").permitAll()
	                    .requestMatchers("/signup", "/", "/login", "/logout").permitAll()
	                    .requestMatchers("/eventos/detalle/**", "/eventos/destacados", "/eventos/activos", "/eventos/tipo/**", "/tipo/**", "/eventos/misReservas").permitAll()
	                    .requestMatchers("/rest/encriptar/**").permitAll()
	                    .requestMatchers("/reservas/**").hasAnyAuthority("ROLE_CLIENTE")
	                    .anyRequest().authenticated())
	            .formLogin(form -> form
	                    .loginPage("/login")
	                    .failureUrl("/login?error") 
	                    .permitAll())
	            .logout(logout -> logout.logoutSuccessUrl("/"));
	    return http.build();
	}

}
