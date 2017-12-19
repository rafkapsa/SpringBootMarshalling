package com.javasampleapproach.marshalling.converter;

import com.javasampleapproach.marshalling.exception.AppException;
import com.javasampleapproach.marshalling.exception.WriterToFileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.XmlMappingException;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;

import javax.xml.transform.stream.StreamResult;
import java.io.Closeable;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class XmlConverter {

    @Autowired
    private Jaxb2Marshaller marshaller;

    public void convertFromObjectToXML(Object object, String filepath) {
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(filepath);
            marshaller.marshal(object, new StreamResult(os));
        } catch (IOException | XmlMappingException ex) {
            throw new WriterToFileException(ex.getMessage(), ex);
        } finally {
            if (os != null) {
                close(os);
            }
        }
    }

    private void close(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException ex) {
            throw new AppException(ex.getMessage(), ex);
        }
    }
}
