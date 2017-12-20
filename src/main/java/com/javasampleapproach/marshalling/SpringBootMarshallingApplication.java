package com.javasampleapproach.marshalling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.javasampleapproach.marshalling.converter.CsvConverter;
import com.javasampleapproach.marshalling.converter.XmlConverter;
import com.javasampleapproach.marshalling.model.Text;
import com.javasampleapproach.marshalling.service.DataProvider;
import com.javasampleapproach.marshalling.service.DataProvider.DataProviderListener;

@SpringBootApplication
public class SpringBootMarshallingApplication implements CommandLineRunner, DataProviderListener {

    @Autowired
    private XmlConverter xmlConverter;
    @Autowired
    private CsvConverter csvConverter;

    final String XML_FILE_SMALL = "small.xml";
    final String CSV_FILE_SMALL = "small.csv";
    
    final String XML_FILE_LARGE = "large.xml";
    final String CSV_FILE_LARGE = "large.csv";
    private Text text;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMarshallingApplication.class, args);
    }

    public void run(String... args) throws Exception {
        new DataProvider(this).execute("small.in");
        xmlConverter.convertFromObjectToXML(text, XML_FILE_LARGE);
        csvConverter.convertFromObjectToCSV(text, CSV_FILE_LARGE);
    }

    public void onDataLoaded(Text result) {
        this.text = result;
    }


}