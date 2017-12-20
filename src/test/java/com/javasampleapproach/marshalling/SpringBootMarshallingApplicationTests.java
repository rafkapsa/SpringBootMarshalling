package com.javasampleapproach.marshalling;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.javasampleapproach.marshalling.model.Sentence;
import com.javasampleapproach.marshalling.model.Text;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMarshallingApplicationTests {
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testCreate() {
		
		List<Sentence> sentences = new ArrayList<Sentence>();
		List<String> words = new ArrayList<String>();
		
		Text text = new Text();
		text.setSentences(sentences);
		
		Sentence s1 = new Sentence();
		s1.setWords(words);
		s1.getWords().add("Darek");
		s1.getWords().add("Rafal");
		
		Sentence s2 = new Sentence();
		s2.setWords(words);
		s2.getWords().add("Fight");
		s2.getWords().add("animal");
		
		text.getSentences().add(s1);
		text.getSentences().add(s2);
		
		Assert.assertNotNull(text);
		Assert.assertNotNull(s1);
		Assert.assertNotNull(s2);	
	}

}
