package com.javasampleapproach.marshalling.config;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.oxm.castor.CastorMarshaller;

import com.javasampleapproach.marshalling.csvconvert.CsvConverter;
import com.javasampleapproach.marshalling.xmlconvert.XmlConverter;

@Configuration
public class AppConfig {
		
	@Bean 
	XmlConverter xmlConverter(){
		XmlConverter xmlConverter = new XmlConverter();
		xmlConverter.setMarshaller(castorMarshaller());
		xmlConverter.setUnmarshaller(castorMarshaller());
		return xmlConverter;
	}
	
	@Bean
	public CastorMarshaller castorMarshaller(){
		CastorMarshaller castorMarshaller = new CastorMarshaller();
		Resource resource = new ClassPathResource("mapping.xml");
		castorMarshaller.setMappingLocation(resource);
		return castorMarshaller;
	}
	
	@Bean
	CsvConverter csvConverter() {
		CsvConverter csvConverter = new CsvConverter();
		
		return csvConverter;
	}
}
