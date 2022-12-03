package med.voll.api.configurations;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CorsConfiguration implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(final CorsRegistry registry) {
		registry.addMapping( "/**" )
			.allowedOrigins( "http://localhost:3000" )
			.allowedMethods( "GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT" );
	}
}