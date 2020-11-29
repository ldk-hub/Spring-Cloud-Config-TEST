package com.micro.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

//?ä§?îÑÎß? IoC Ïª®ÌÖå?ù¥?ÑàÍ∞? ?ï¥?ãπ ?Å¥?ûò?ä§Î•? Îπ? ?†ï?ùò?ùò ?Üå?ä§Î°? ?Ç¨?ö©?ïú?ã§?äî Í≤ÉÏùÑ ?Çò???Ç∏?ã§.
@Configuration
public class TitleConfiguration {
	// ???ùºÏ¶? ?†Å?ö© Config
	@Bean
	public TilesConfigurer tilesConfigurer() {
		final TilesConfigurer configurer = new TilesConfigurer();
		configurer.setDefinitions(new String[] { "WEB-INF/tiles/tiles.xml" });
		configurer.setCheckRefresh(true);
		return configurer;
	}

	@Bean//Î∑∞Î¶¨Ï°∏Î≤Ñ
	public TilesViewResolver tilesViewResolver() {
		final TilesViewResolver resolver = new TilesViewResolver();
		resolver.setViewClass(TilesView.class);
		return resolver;
	}
}
