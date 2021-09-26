package com.co.arquitectura.management;

import com.co.arquitectura.management.adapter.api.ApiConst;
import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.scheduling.annotation.EnableAsync;

@EntityScan({"com.co.arquitectura.management.domain.repository.model.database"})
@SpringBootApplication
@EnableAsync
public class Application {

	final static Logger logger = Logger.getLogger(Application.class);
    
    public static void main(String[] args) {
         ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(Application.class)
		.properties("spring.config.name:local.application")
		.build()
		.run(args);
		 ConfigurableEnvironment configurableEnvironment = applicationContext.getEnvironment();
         ApiConst.CONFIGURABLE_PROPERTIES = configurableEnvironment;
		logger.debug("environment.getProperty(\"spring.datasource.driver-class-name\") " + configurableEnvironment.getProperty("spring.datasource.driver-class-name"));
		 logger.debug("environment.getProperty(\"spring.jpa.show-sql\") " + configurableEnvironment.getProperty("spring.datasource.url"));
    }

}
