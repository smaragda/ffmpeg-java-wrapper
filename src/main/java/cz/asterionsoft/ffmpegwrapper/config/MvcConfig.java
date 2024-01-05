package cz.asterionsoft.ffmpegwrapper.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;


@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
				.addResourceHandler("/out/**")
				.addResourceLocations("file:out/")
				.setCachePeriod(0)
				.resourceChain(false)
				.addResolver(new PathResourceResolver());
	}
}