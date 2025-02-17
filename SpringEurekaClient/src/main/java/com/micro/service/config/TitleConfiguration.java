package com.micro.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

//?€?λ§? IoC μ»¨ν?΄?κ°? ?΄?Ή ?΄??€λ₯? λΉ? ? ?? ??€λ‘? ?¬?©??€? κ²μ ????Έ?€.
@Configuration
public class TitleConfiguration {
	// ???Όμ¦? ? ?© Config
	@Bean
	public TilesConfigurer tilesConfigurer() {
		final TilesConfigurer configurer = new TilesConfigurer();
		configurer.setDefinitions(new String[] { "WEB-INF/tiles/tiles.xml" });
		configurer.setCheckRefresh(true);
		return configurer;
	}

	@Bean//λ·°λ¦¬μ‘Έλ²
	public TilesViewResolver tilesViewResolver() {
		final TilesViewResolver resolver = new TilesViewResolver();
		resolver.setViewClass(TilesView.class);
		return resolver;
	}
}
