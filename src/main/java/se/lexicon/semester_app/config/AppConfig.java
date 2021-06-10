package se.lexicon.semester_app.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    //added modelMapper dependencies to mvn
    @Bean
    public ModelMapper modelMapper() {

        return new ModelMapper();
    }

}
