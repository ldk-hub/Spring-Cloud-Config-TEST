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


//?€?λ§? IoC μ»¨ν?΄?κ°? ?΄?Ή ?΄??€λ₯? λΉ? ? ?? ??€λ‘? ?¬?©??€? κ²μ ????Έ?€.
@Configuration
/*@EnableWebMvc??: web mvc? ?΄?©???° ??΄? spring containerκ°? κ°?? Έ?Ό?  κΈ°λ³Έ? ?Έ bean component? ?±λ‘ν΄? λΉ λ₯΄κ²? ?Έ?κ²? mvc? κ΅¬μΆ? ? ?? configuration ?κ²½μ ? κ³΅ν΄μ€??€.
	?λ₯? ?€λ©? spring3 ?? ?λ‘?κ²? ? ??κ³? ?? @MVC (@RequestMapping, @Requestbody, @ResponseBody)?±? 
	?€???Ό? ??΄? ?±λ‘λ?΄?Ό ?? RequestMappingHandler,RequestMappingHandlerAdapter,ExceptionHandlerExceptionResolver ?±? ?±λ‘μ ???Όλ‘? ?΄μ€??€.
	XML base???€λ©? <mvc:annotation-driven/>κ³? κ°λ€. */
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
	
	//?Έμ½λ© λ¬Έμ  μ½λμ§?λ§? ??©?? λ¬Έμ μ°Ύμκ²?
	@Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
    }
	//?Έμ½λ© λ¬Έμ  μ½λμ§?λ§? ??©?? λ¬Έμ μ°Ύμκ²POST
    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }
    

    /**
     * Jackson Object Mapper Bean ?±λ‘?
     * @return ObjectMapper ??± ? Gsonλ³΄λ€ ?±?₯?΄?¨?΄μ§?κΈ°λλ¬Έμ ?±κΈ??€ λ°©μ?Όλ‘? ?ΈμΆν  κ²½μ° κ·? λ¬Έμ κ°? ?΄κ²°λ?€.
     * ?±κΈ??€ μ£Όμ??¬ ?¬?© Spring Boot ? application.properties λ‘? ?€? ?? κ²? ?λ¬Έμ μ£Όμ ??κ³? ?°? κ²½μ°? ?μ§?λ§?, μ£Όμ?΄? singleton ?Όλ‘? ?°?κ²? λΆ??λ₯? μ€μ΄? λ°©λ²?΄?€
		zepinos? λ°°λ? ?
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
