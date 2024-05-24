package com.example.kelimeezberleme;

import java.time.*;

public class Words { //Kelimeleri listelemek için gerekl şablon oluşturur.
    private String english,turkish,sentence;
    private String no,correctNo,consecutiveCorrectNo;
    private LocalDate lastDate;

    public Words(String english, String turkish, String sentence, String no, String correctNo, String consecutiveCorrectNo,LocalDate lastDate) {
        this.english = english;
        this.turkish = turkish;
        this.sentence = sentence;
        this.no = no;
        this.correctNo = correctNo;
        this.consecutiveCorrectNo = consecutiveCorrectNo;
        this.lastDate = lastDate;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public void setTurkish(String turkish) {
        this.turkish = turkish;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public void setCorrectNo(String correctNo) {
        this.correctNo = correctNo;
    }

    public void setConsecutiveCorrectNo(String consecutiveCorrectNo) {
        this.consecutiveCorrectNo = consecutiveCorrectNo;
    }

    public void setLastDate(LocalDate lastDate) {
        this.lastDate = lastDate;
    }

    public String getEnglish() {
        return english;
    }

    public String getTurkish() {
        return turkish;
    }

    public String getSentence() {
        return sentence;
    }

    public String getNo() {
        return no;
    }

    public String getCorrectNo() {
        return correctNo;
    }

    public String getConsecutiveCorrectNo() {
        return consecutiveCorrectNo;
    }

    public LocalDate getLastDate() {
        return lastDate;
    }
}
