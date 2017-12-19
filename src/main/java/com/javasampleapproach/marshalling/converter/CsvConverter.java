package com.javasampleapproach.marshalling.converter;

import com.javasampleapproach.marshalling.exception.AppException;
import com.javasampleapproach.marshalling.exception.WriterToFileException;
import com.javasampleapproach.marshalling.model.Sentence;
import com.javasampleapproach.marshalling.model.Text;
import org.springframework.stereotype.Service;

import java.io.Closeable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
public class CsvConverter {

    public void convertFromObjectToCSV(Text text, String filepath) {
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(filepath);
            os.write(getCsvString(text).getBytes(StandardCharsets.UTF_8));
        } catch (IOException ex) {
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

    private String getCsvString(Text text) {

        // Getting Sentence object, which have the maximum quantity of words
        Optional<Sentence> maxWords = text.getSentences().stream().max((s1, s2) -> Integer.compare(s1.getWords().size(), s2.getWords().size()));
        int maxWordsCount = maxWords.get().getWords().size();


        String columnHeaders = "";
        // Creating CSV headers
        for (int i = 1; i <= maxWordsCount; i++) {
            columnHeaders += ", " + "Word " + i;
        }

        // Creating body of CSV
        String csvBody = columnHeaders + "\n";
        int index = 1;
        for (Sentence sentence : text.getSentences()) {
            csvBody += "Sentence " + index;

            for (String word : sentence.getWords()) {
                csvBody += ", " + word;
            }
            csvBody += "\n";
            index++;
        }
        return csvBody;
    }

}
