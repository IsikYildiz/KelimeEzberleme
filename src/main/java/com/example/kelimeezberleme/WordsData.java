package com.example.kelimeezberleme;

import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class WordsData {
    public static void addImage(Connection con, String name, String image) throws SQLException, FileNotFoundException {//Girilen kelime resmini ekler.
        File imageFile= new File(image);
        FileInputStream imageCode= new FileInputStream(imageFile);
        PreparedStatement setValues = con.prepareStatement("UPDATE \"Kelimeler\" SET resim=(?) WHERE parola=(SELECT parola FROM \"Kullanici\" WHERE isim='"+name+"')");
        setValues.setBinaryStream(1, imageCode,(int) imageFile.length());
        setValues.executeUpdate();
    }

    public static void addWord(Connection con, String name, TextField word, TextField turkish, TextField sentence) throws SQLException {//Girilen bilgilerle kelime ekler.
        PreparedStatement stmt= con.prepareStatement("SELECT parola FROM \"Kullanici\" WHERE isim='"+name+"'");
        ResultSet rs=stmt.executeQuery();
        String parola="";
        if(rs.next()){
            parola=rs.getString(1);
        }
        PreparedStatement setValues = con.prepareStatement("INSERT INTO \"Kelimeler\"(ingilizce,turkce,cumle,parola) VALUES(?,?,?,?)");
        setValues.setString(1, word.getText());
        setValues.setString(2, turkish.getText());
        setValues.setString(3, sentence.getText());
        setValues.setString(4, parola);
        setValues.executeUpdate();
    }

    public static void deleteWord(Connection con, String name, TextField word) throws SQLException {//Girilen kelimeyi siler.
        PreparedStatement delete = con.prepareStatement("DELETE FROM \"Kelimeler\" Where ingilizce='"+word.getText()+"' and parola=(SELECT parola FROM \"Kullanici\" WHERE isim='"+name+"')");
        delete.executeQuery();
    }

    public static int getQuestionNumber(Connection con,String name) throws SQLException { //Kullanıcının istediği soru sayısını alır.
        PreparedStatement getQuestionNumber = con.prepareStatement("select sorusayisi FROM \"Kullanici\"  WHERE isim='"+name+"'");
        ResultSet rs=getQuestionNumber.executeQuery();
        int questionNumber = 0;
        while (rs.next()){
            questionNumber=rs.getInt(1);
        }
        return questionNumber;
    }

    public static void setQuestionNumber(Connection con,String name,int number) throws SQLException {//Kullanıcının  soru sayısını değiştirir.
        PreparedStatement setQuestionNumber = con.prepareStatement("update \"Kullanici\" set sorusayisi="+number+" WHERE isim='"+name+"'");
        setQuestionNumber.executeUpdate();
    }
    public static List getList(Connection con, String name) throws SQLException { //Kelimeleri içeren bir liste oluşturur.
        List<Words> words = new ArrayList<>(List.of());
        String sql = "SELECT ingilizce,turkce,cumle,cikmasayisi,dogrubilmesayisi,dogrusayisi,sontarih FROM "Kelimeler" WHERE parola=(SELECT parola FROM "Kullanici" WHERE isim='"+name+"')";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            int percent=0;
            if(rs.getInt(5)!=0){
                percent=rs.getInt(5)*100/rs.getInt(4);
            }
            String percentString= String.valueOf(percent);
            LocalDate date = rs.getDate(7).toLocalDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate cmp = LocalDate.parse("2024-01-01", formatter);
            if(date.isEqual(cmp))
                date=null;
            words.add(new Words(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),percentString,rs.getString(6),date));
        }
        return words;
    }
    public static String getTurkish(Connection con,String word) throws SQLException {//Karşılaştırma yapmak için verilen kelimenin türkesini gönderir.
        PreparedStatement getTurkish=con.prepareStatement("select turkce from "Kelimeler" where ingilizce='"+word+"'");
        ResultSet rs=getTurkish.executeQuery();
        String cevap = "";
        while (rs.next()){
            cevap= rs.getString(1);
        }
        return cevap;
    }
    public static void updateCorrectWord(Connection con,String word) throws SQLException {//Kelime "doğru" olarak güncellenir.
        PreparedStatement updateWord=con.prepareStatement("update "Kelimeler" set dogrusayisi=dogrusayisi+1,cikmasayisi=cikmasayisi+1,dogrubilmesayisi=dogrubilmesayisi+1,sonTarih=CURRENT_DATE where ingilizce='"+word+"'");
        updateWord.executeUpdate();
    }

    public static void updateWrongWord(Connection con,String word) throws SQLException {//Kelime "yanlış" olarak güncellenir.
        PreparedStatement updateWord=con.prepareStatement("update "Kelimeler" set dogrusayisi=0,cikmasayisi=cikmasayisi+1,sonTarih=CURRENT_DATE where ingilizce='"+word+"'");
        updateWord.executeUpdate();
    }
}
