package com.example.kelimeezberleme;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.SQLException;

public class SettingsScreenController {
    @FXML
    private Button addButton;

    @FXML
    private Button backButton;

    @FXML
    private Label noNumber;

    @FXML
    private Button questionButton;

    @FXML
    private TextField questionNumber;

    @FXML
    private TextField delete;

    @FXML
    private Button deleteButton;

    @FXML
    void deleteWord(ActionEvent event) throws SQLException {//Girilen kelimeyi siler.
        Connection con=Data.connect();
        if(!delete.getText().isEmpty()){
            WordsData.deleteWord(con,LoginScreenController.getNameString(),delete);
            noNumber.setText("Kelime Başarıyla Silinmiştir.");
            noNumber.setVisible(true);
            con.close();
        }
        else {
            noNumber.setText("Lütfen Kelimeyi Giriniz.");
            noNumber.setVisible(true);
        }
        con.close();
    }

    @FXML
    void addWord(ActionEvent event) {//Kelime ekleme ekranını açar
        try {
            Parent root= FXMLLoader.load(getClass().getResource("addword-screen.fxml"));
            addButton.getScene().setRoot(root);
        }catch (Exception e){
            System.out.println("Sayfa açılamadı.");
        }
    }

    @FXML
    void goBack(ActionEvent event) {//Ana menüye döner.
        try {
            Parent root= FXMLLoader.load(getClass().getResource("mainmenu-screen.fxml"));
            backButton.getScene().setRoot(root);
        }catch (Exception e){
            System.out.println("Sayfa açılamadı.");
        }
    }

    @FXML
    void setQuestionNumber(ActionEvent event) throws SQLException {//Kullanıcın test soru sayısını kaydeder.
        Connection con=Data.connect();
        if(!questionNumber.getText().isEmpty()){
            int number=Integer.parseInt(questionNumber.getText());
            WordsData.setQuestionNumber(con,LoginScreenController.getNameString(),number);
            noNumber.setText("Soru sayısı değiştirilmiştir.");
            noNumber.setVisible(true);
            con.close();
        }
        else {
            noNumber.setText("Lütfen soru sayısını giriniz.");
            noNumber.setVisible(true);
        }
        con.close();
    }
}
