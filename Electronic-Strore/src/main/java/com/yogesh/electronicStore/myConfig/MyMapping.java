package com.yogesh.electronicStore.myConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;

@Configuration
public class MyMapping {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
