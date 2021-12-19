package sii.itconference;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ItConferenceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItConferenceApplication.class, args);
    }

    //TODO przenieść do konfiguracji
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
