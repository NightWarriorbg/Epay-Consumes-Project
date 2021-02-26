package me.nightwarrior.epayconsumes.config;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@ConfigurationPropertiesScan
@Configuration
public class ServerConfig {

    @Primary
    @Bean
    public ServerProperties serverProperties() {
        return new IgnoreUnknownFieldsServerProperties();
    }

    @ConfigurationProperties(prefix = "server", ignoreUnknownFields = true)
    public static class IgnoreUnknownFieldsServerProperties extends ServerProperties {

    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.setConnectTimeout(Duration.ofMillis(3000)).setReadTimeout(Duration.ofMillis(3000)).build();
    }

}