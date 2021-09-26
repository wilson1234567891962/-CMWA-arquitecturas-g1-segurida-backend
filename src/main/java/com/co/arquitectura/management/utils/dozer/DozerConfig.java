package com.co.arquitectura.management.utils.dozer;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DozerConfig {

	final static Logger logger = Logger.getLogger(DozerConfig.class);
	
    @Bean
    public DozerBeanMapper dozerMapperConfig() {
        final DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.setMappingFiles(Arrays.asList("dozer_mapping.xml"));
        return mapper;
    }
}
