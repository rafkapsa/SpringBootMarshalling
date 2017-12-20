package com.javasampleapproach.marshalling.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Sentence implements Serializable {

    private List<String> words;

    public Sentence() {
        words = new ArrayList<String>();
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }
}
