package com.example.learnthewords.model;

public class Words {
    private int id;
    private String originWord;
    private String translate;

    public Words(String originWord, String translate) {
        this.originWord = originWord;
        this.translate = translate;
    }

    public Words(int id, String originWord, String translate) {
        this.id = id;
        this.originWord = originWord;
        this.translate = translate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginWord() {
        return originWord;
    }

    public void setOriginWord(String originWord) {
        this.originWord = originWord;
    }

    public String getTranslateWord() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

}