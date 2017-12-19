package com.javasampleapproach.marshalling;

import com.javasampleapproach.marshalling.converter.CsvConverter;
import com.javasampleapproach.marshalling.converter.XmlConverter;
import com.javasampleapproach.marshalling.model.Text;
import com.javasampleapproach.marshalling.service.DataProvider;
import com.javasampleapproach.marshalling.service.DataProvider.DataProviderListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootMarshallingApplication implements CommandLineRunner, DataProviderListener {

    @Autowired
    private XmlConverter xmlConverter;
    @Autowired
    private CsvConverter csvConverter;

    final String XMLFILE = "small.xml";
    final String CSVFILE = "small.csv";
    private Text text;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMarshallingApplication.class, args);
    }

    public void run(String... args) throws Exception {
        new DataProvider(this).execute("small.in");
        xmlConverter.convertFromObjectToXML(text, XMLFILE);
        csvConverter.convertFromObjectToCSV(text, CSVFILE);
    }

    public void onDataLoaded(Text result) {
        this.text = result;
    }


}
