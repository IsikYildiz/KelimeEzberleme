package com.example.kelimeezberleme;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;

public class AddWordController {
    @FXML
    private Button addButton;

    @FXML
    private Button backButton;

    @FXML
    private TextField image;

    @FXML
    private Button imageButton;

    @FXML
    private Label noTurkish;

    @FXML
    private Label noWord;

    @FXML
    private TextField sentence;

    @FXML
    private TextField turkish;

    @FXML
    private TextField word;

    @FXML
    private Label succes;

    @FXML
    void addWord(ActionEvent event) throws SQLException { //Kelimeyi ve türkçesini (varsa cümleyi) alıp veritabanındaki kelime tablosuna ekler.
        if(!word.getText().isEmpty()){
            if(!turkish.getText().isEmpty()) {
                Connection con= Data.connect();
                WordsData.addWord(con,LoginScreenController.getNameString(),word,turkish,sentence);
                con.close();
                imageButton.setVisible(true);
                succes.setVisible(true);
                image.setVisible(true);
                word.setVisible(false);
                turkish.setVisible(false);
                sentence.setVisible(false);
                addButton.setVisible(false);
                noWord.setVisible(false);
                noTurkish.setVisible(false);
            }
            else{
                noWord.setVisible(false);
                noTurkish.setVisible(true);
            }
        }
        else {
            noTurkish.setVisible(false);
            noWord.setVisible(true);
        }
    }

    @FXML
    void addImage(ActionEvent event) throws SQLException, FileNotFoundException { //Kelimeye resim ekler.
        Connection con=Data.connect();
        WordsData.addImage(con,LoginScreenController.getNameString(),image.getText());
        succes.setText("Resim Eklendi");
        succes.setVisible(true);
        con.close();
    }

    @FXML
    void goBack(ActionEvent event) { //Ayarlar menüsüne döner.
        try {
            Parent root= FXMLLoader.load(getClass().getResource("settings-screen.fxml"));
            backButton.getScene().setRoot(root);
        }catch (Exception e){
            System.out.println("Sayfa açılamadı.");
        }
    }
}
