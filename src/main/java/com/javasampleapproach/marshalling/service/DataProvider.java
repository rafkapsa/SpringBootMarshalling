package com.javasampleapproach.marshalling.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.BreakIterator;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.javasampleapproach.marshalling.model.Sentence;
import com.javasampleapproach.marshalling.model.Text;

public class DataProvider {

	private static final String[] ABBREVIATIONS = { "Dr.", "Prof.", "Mr.", "Mrs.", "Ms.", "Jr.", "Ph.D." };

	private DataProviderListener listener;

	public DataProvider(DataProviderListener listener) {
		this.listener = listener;
	}

	public void execute(String file) throws IOException {

		String document = "";

		// Loading data from file
		Resource resource = new ClassPathResource(file);
		InputStream in = resource.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		while (reader.ready()) {
			String line = reader.readLine();
			// if (line.trim().isEmpty()) {
			// paragraphs.add(paragraph);
			// paragraph = "";
			// }
			document += line;
		}
		reader.close();

		listener.onDataLoaded(getTextObject(getSentences(document)));
	}

	private Text getTextObject(List<String> sentencesString) {
		Text text = new Text();
		List<Sentence> sentencesList = new ArrayList<Sentence>();

		for (String s : sentencesString) {
			sentencesList.add(getSentenceObject(s));
		}
		text.setSentences(sentencesList);
		return text;
	}

	private Sentence getSentenceObject(String sentence) {

		// Initialize list of words and Word objects
		List<String> words = new ArrayList<String>();

		// Splitting sentence to words using StringTokenizer Class
		StringTokenizer st = new StringTokenizer(sentence, " \t\n\r\f,.:;?![]-()");

		while (st.hasMoreTokens()) {
			words.add(st.nextToken());
		}
		//
		// Sorting list of words using Collator Class
		Collator collator = Collator.getInstance(Locale.ENGLISH);
		Collections.sort(words, collator);

		// Create Sentence Object
		Sentence sentenceObject = new Sentence();
		sentenceObject.setWords(words);

		return sentenceObject;
	}

	public static List<String> getSentences(String document) {

		// Initialize list of sentences as a String Object
		List<String> sentenceList = new ArrayList<String>();

		// Document preparation
		document = document.replaceAll("\\.", ". ");

		// Splitting document to sentences using BreakIterator class
		BreakIterator bi = BreakIterator.getSentenceInstance(Locale.ENGLISH);
		bi.setText(document);
		int start = bi.first();
		int end = bi.next();
		int tempStart = start;
		while (end != BreakIterator.DONE) {
			String sentence = document.substring(start, end);
			if (!hasAbbreviation(sentence)) {
				sentence = document.substring(tempStart, end);
				tempStart = end;
				sentenceList.add(sentence);
			}
			start = end;
			end = bi.next();
		}
		return sentenceList;
	}

	private static boolean hasAbbreviation(String sentence) {
		if (sentence == null || sentence.isEmpty()) {
			return false;
		}
		for (String w : ABBREVIATIONS) {
			if (sentence.contains(w)) {
				return true;
			}
		}
		return false;
	}

	public interface DataProviderListener {
		void onDataLoaded(Text result);
	}
}
