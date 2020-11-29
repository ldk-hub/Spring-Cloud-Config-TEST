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


//?��?���? IoC 컨테?��?���? ?��?�� ?��?��?���? �? ?��?��?�� ?��?���? ?��?��?��?��?�� 것을 ?��???��?��.
@Configuration
/*@EnableWebMvc??: web mvc?�� ?��?��?��?��?�� ?��?��?�� spring container�? �??��?��?�� 기본?��?�� bean component?�� ?��록해?�� 빠르�? ?��?���? mvc?�� 구축?��?�� ?��?�� configuration ?��경을 ?��공해�??��.
	?���? ?���? spring3 ?��?�� ?���?�? ?��?��?���? ?��?�� @MVC (@RequestMapping, @Requestbody, @ResponseBody)?��?�� 
	?��???��?�� ?��?��?�� ?��록되?��?�� ?��?�� RequestMappingHandler,RequestMappingHandlerAdapter,ExceptionHandlerExceptionResolver ?��?�� ?��록을 ?��?��?���? ?���??��.
	XML base???���? <mvc:annotation-driven/>�? 같다. */
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
	
	//?��코딩 문제 코드�?�? ?��?��?��?�� 문제찾을�?
	@Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
    }
	//?��코딩 문제 코드�?�? ?��?��?��?�� 문제찾을것POST
    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }
    

    /**
     * Jackson Object Mapper Bean ?���?
     * @return ObjectMapper ?��?�� ?�� Gson보다 ?��?��?��?��?���?기때문에 ?���??�� 방식?���? ?��출할 경우 �? 문제�? ?��결된?��.
     * ?���??�� 주입?��?�� ?��?�� Spring Boot ?�� application.properties �? ?��?��?��?�� �? ?��문에 주입 ?��?���? ?��?�� 경우?�� ?���?�?, 주입?��?�� singleton ?���? ?��?���? �??���? 줄이?�� 방법?��?��
		zepinos?��배님?�� ?��
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
