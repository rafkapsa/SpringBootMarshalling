package com.javasampleapproach.marshalling.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "text")
public class Text implements Serializable {

    private List<Sentence> sentences;

    public Text() {
        sentences = new ArrayList<Sentence>();
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

    public void setSentences(List<Sentence> sentences) {
        this.sentences = sentences;
    }

}
