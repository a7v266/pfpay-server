package ru.pfpay.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebMvc
@EnableJSONDoc
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        String[] allowedHeaders = {
                "Accept", "Content-Type", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Authorization", "X-Auth-Token", "X-Requested-With"
        };

        String[] allowedMethods = {
                "GET", "PUT", "POST", "DELETE", "OPTIONS"
        };

        registry.addMapping("/**").allowedOrigins("*").allowCredentials(true).allowedHeaders(allowedHeaders).allowedMethods(allowedMethods);

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("**/*.css", "**/*.js", "**/*.map", "*.html").addResourceLocations("classpath:META-INF/resources/").setCachePeriod(0);
    }

    /*
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.disable(
                MapperFeature.USE_GETTERS_AS_SETTERS,
                MapperFeature.AUTO_DETECT_CREATORS,
                MapperFeature.AUTO_DETECT_FIELDS,
                MapperFeature.AUTO_DETECT_GETTERS,
                MapperFeature.AUTO_DETECT_SETTERS,
                MapperFeature.AUTO_DETECT_IS_GETTERS);

        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        objectMapper.setVisibility(
                objectMapper.getSerializationConfig().getDefaultVisibilityChecker()
                        .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                        .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
                        .withIsGetterVisibility(JsonAutoDetect.Visibility.NONE)
                        .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
                        .withCreatorVisibility(JsonAutoDetect.Visibility.NONE));


        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        converters.add(new MappingJackson2HttpMessageConverter(objectMapper));
    }
    */

}