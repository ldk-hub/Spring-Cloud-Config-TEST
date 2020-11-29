package com.micro.service.config;

import java.nio.charset.Charset;

import javax.servlet.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


//?Š¤?”„ë§? IoC ì»¨í…Œ?´?„ˆê°? ?•´?‹¹ ?´?˜?Š¤ë¥? ë¹? ? •?˜?˜ ?†Œ?Š¤ë¡? ?‚¬?š©?•œ?‹¤?Š” ê²ƒì„ ?‚˜???‚¸?‹¤.
@Configuration
/*@EnableWebMvc??: web mvc?„ ?´?š©?•˜?Š”?° ?ˆ?–´?„œ spring containerê°? ê°?? ¸?•¼?•  ê¸°ë³¸? ?¸ bean component?„ ?“±ë¡í•´?„œ ë¹ ë¥´ê²? ?¸?•˜ê²? mvc?„ êµ¬ì¶•?• ?ˆ˜ ?ˆ?Š” configuration ?™˜ê²½ì„ ? œê³µí•´ì¤??‹¤.
	?˜ˆë¥? ?“¤ë©? spring3 ?—?„œ ?ƒˆë¡?ê²? ? œ?‹œ?•˜ê³? ?ˆ?Š” @MVC (@RequestMapping, @Requestbody, @ResponseBody)?“±?˜ 
	?Š¤???¼?„ ?œ„?•´?„œ ?“±ë¡ë˜?–´?•¼ ?•˜?Š” RequestMappingHandler,RequestMappingHandlerAdapter,ExceptionHandlerExceptionResolver ?“±?˜ ?“±ë¡ì„ ??™?œ¼ë¡? ?•´ì¤??‹¤.
	XML base???‹¤ë©? <mvc:annotation-driven/>ê³? ê°™ë‹¤. */
@EnableWebMvc
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {
   
	@Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");

        return resolver;
    }
	
	//?¸ì½”ë”© ë¬¸ì œ ì½”ë“œì§?ë§? ?†Œ?š©?—†?Œ ë¬¸ì œì°¾ì„ê²?
	@Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
    }
	//?¸ì½”ë”© ë¬¸ì œ ì½”ë“œì§?ë§? ?†Œ?š©?—†?Œ ë¬¸ì œì°¾ì„ê²ƒPOST
    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }
    

    /**
     * Jackson Object Mapper Bean ?“±ë¡?
     * @return ObjectMapper ?ƒ?„± ?‹œ Gsonë³´ë‹¤ ?„±?Š¥?´?–¨?–´ì§?ê¸°ë•Œë¬¸ì— ?‹±ê¸??†¤ ë°©ì‹?œ¼ë¡? ?˜¸ì¶œí•  ê²½ìš° ê·? ë¬¸ì œê°? ?•´ê²°ëœ?‹¤.
     * ?‹±ê¸??†¤ ì£¼ì…?•˜?—¬ ?‚¬?š© Spring Boot ?˜ application.properties ë¡? ?„¤? •?•˜?Š” ê²? ?•Œë¬¸ì— ì£¼ì… ?•ˆ?•˜ê³? ?“°?Š” ê²½ìš°?„ ?ˆì§?ë§?, ì£¼ì…?•´?„œ singleton ?œ¼ë¡? ?“°?Š”ê²? ë¶??•˜ë¥? ì¤„ì´?Š” ë°©ë²•?´?‹¤
		zepinos?„ ë°°ë‹˜?˜ ?Œ
     */
    @Bean
    public ObjectMapper objectMapper() {
      return Jackson2ObjectMapperBuilder
          .json()
          .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
          .modules(new JavaTimeModule())
          .propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
          .build();
    }

    @Override
    public void addResourceHandlers( ResourceHandlerRegistry registry )
    {
        registry.addResourceHandler( "/static/**" ).addResourceLocations( "classpath:/static/" );
    }
}
