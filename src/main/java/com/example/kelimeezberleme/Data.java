package com.example.kelimeezberleme;

import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import java.sql.*;


public class Data { //Veritabanındaki bilgi işlemleri için bir sınıf
    public static Connection connect() throws SQLException { //Database bağlantısını sağlar.
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/KelimeEzberleme", "postgres", "lqs20.");
    }

    public static void insertValueUser(Connection con, TextField name, TextField password, RadioMenuItem question, TextField answer) throws SQLException { //Connection,isim,parola,soru ve cevap bilgilerini alır ve Kullanici tablosuna kaydeder.
        PreparedStatement setUser = con.prepareStatement("INSERT INTO \"Kullanici\" (isim, parola, soru,cevap) VALUES (?, ?, ?,?)");
        setUser.setString(1, name.getText());
        setUser.setString(2, password.getText());
        setUser.setString(3, question.getText());
        setUser.setString(4, answer.getText());
        setUser.executeUpdate();
    }

    public static void updatePassword(Connection con, TextField password, String name) throws SQLException { //Şifre ve isim bilgilerini alıp ve şifreyi günceller.
        PreparedStatement setWordsPassword = con.prepareStatement("UPDATE \"Kelimeler\" SET parola=? WHERE parola=(select parola from \"Kullanici\" where isim='"+name+"')");
        setWordsPassword.setString(1, password.getText());
        setWordsPassword.executeUpdate();
        PreparedStatement setPassword = con.prepareStatement("UPDATE \"Kullanici\" SET parola=? WHERE isim='" + name + "'");
        setPassword.setString(1, password.getText());
        setPassword.executeUpdate();
    }

    public static boolean compareUser(Connection con, TextField name, TextField password) throws SQLException { //Kullanıcının girdiği ad ve şifre bilgilerini kontrol eder.
        Statement compareUser = con.createStatement();
        ResultSet rs = compareUser.executeQuery("SELECT * FROM \"Kullanici\"");
        while (rs.next()) {
            if (name.getText().equals(rs.getString(1)) && password.getText().equals(rs.getString(2))) {
                return true;
            }
        }
        return false;
    }

    public static boolean comparePassword(Connection con, TextField password) throws SQLException { //Girilen parola doğru mu?
        Statement comparePassword = con.createStatement();
        ResultSet rs = comparePassword.executeQuery("SELECT parola FROM \"Kullanici\"");
        while (rs.next()) {
            if (password.getText().equals(rs.getString(1)))
                return true;
        }
        return false;
    }

    public static boolean compareName(Connection con, TextField name) throws SQLException { //Girilen isim doğru mu?
        Statement compareName = con.createStatement();
        ResultSet rs = compareName.executeQuery("SELECT isim FROM \"Kullanici\"");
        while (rs.next()) {
            if (name.getText().equals(rs.getString(1)))
                return true;
        }
        return false;
    }

    public static String findQuestion(Connection con, String name) throws SQLException {//Kullanıcının ismiyle girdiği cevabı bulur.
        Statement findQuestion = con.createStatement();
        String soru = "";
        ResultSet rs = findQuestion.executeQuery("SELECT soru FROM \"Kullanici\" WHERE isim='" + name + "'");
        if (rs.next()) {
            soru = rs.getString(1);
        }
        return soru;
    }

    public static String findAnswer(Connection con, String name) throws SQLException { //Kullanıcının ismiyle seçtiği soruyu bulur.
        Statement findAnswer = con.createStatement();
        String cevap = "";
        ResultSet rs = findAnswer.executeQuery("SELECT cevap FROM \"Kullanici\" WHERE isim='" + name + "'");
        if (rs.next()) {
            cevap = rs.getString(1);
        }
        return cevap;
    }
}
