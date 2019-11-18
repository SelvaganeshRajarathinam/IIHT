package com.cts.nr;

import com.cts.filter.CORSFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.dozer.DozerBeanMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = {"com.cts", "com.cts.service"})
@EntityScan("com.cts.entity")
@EnableJpaRepositories("com.cts.repository")
public class NrApplication {
	private static final Logger LOGGER = LogManager.getLogger(NrApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(NrApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean corsFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		LOGGER.info("CORS filter ready!");
		registrationBean.setFilter(new CORSFilter());
		registrationBean.addUrlPatterns("/*");
		return registrationBean;
	}

	@Bean
	public DozerBeanMapper dozerMapper() {
		List<String> mappingFiles = Arrays.asList("mapper-dozer.xml");
		DozerBeanMapper dozerBean = new DozerBeanMapper();
		LOGGER.info("setting  the mapping files using dozer mapper");
		dozerBean.setMappingFiles(mappingFiles);
		return dozerBean;
	}

	/*@Bean
	public Clock micrometerClock() {
		return Clock.SYSTEM;
	}*/

/*
	@Primary
	@Bean
	public MetricsEndpoint metricsEndpoint(SimpleMeterRegistry registry) {
		return new MetricsEndpoint(registry);
	}
*/



}

