package com.javasampleapproach.marshalling.csvconvert;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Optional;

import com.javasampleapproach.marshalling.model.Sentence;
import com.javasampleapproach.marshalling.model.Text;

public class CsvConverter {

	public void convertFromObjectToCSV(Text text, String filepath) throws IOException {

		try (FileOutputStream os = new FileOutputStream(filepath)) {
		    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
		    os.write(getCsvString(text).getBytes("UTF-8"));
		    os.close();
		} catch (Exception e) {
			throw e;
		}
	}

	private String getCsvString(Text text) {

		// Getting Sentence object, which have the maximum quantity of words
		Optional<Sentence> maxWords = text.getSentences().stream()
				.max((s1, s2) -> Integer.compare(s1.getWords().size(), s2.getWords().size()));
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
