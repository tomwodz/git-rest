package pl.tomwodz.gitrest.git.infrastructure.configuration;

import feign.codec.Decoder;
import feign.jackson.JacksonDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("pl.tomwodz")
public class Config {
    @Bean
    public Decoder feignDecoder(){
        return new JacksonDecoder();
    }
}
