package com.javasampleapproach.marshalling;

import java.text.BreakIterator;
import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.javasampleapproach.marshalling.config.AppConfig;
import com.javasampleapproach.marshalling.csvconvert.CsvConverter;
import com.javasampleapproach.marshalling.model.Sentence;
import com.javasampleapproach.marshalling.model.Text;
import com.javasampleapproach.marshalling.service.DataProvider;
import com.javasampleapproach.marshalling.service.DataProvider.DataProviderListener;
import com.javasampleapproach.marshalling.xmlconvert.XmlConverter;

@SpringBootApplication
public class SpringBootMarshallingApplication implements CommandLineRunner, DataProviderListener{

	@Resource
	XmlConverter xmlConverter;
	
	@Resource
	CsvConverter csvConverter;
	
	final String XMLFILE = "small.xml";
	final String CSVFILE = "small.csv";
	private Text text;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootMarshallingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		new DataProvider(this).execute("small.in");
		xmlConverter.convertFromObjectToXML(text, XMLFILE);
		csvConverter.convertFromObjectToCSV(text, CSVFILE);
	}

	@Override
	public void onDataLoaded(Text result) {
		this.text = result;	
	}
	
	
}
