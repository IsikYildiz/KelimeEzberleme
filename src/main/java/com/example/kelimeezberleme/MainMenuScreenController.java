package com.example.kelimeezberleme;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainMenuScreenController {
    @FXML
    private Button exitButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Button startButton;

    @FXML
    private Button statisticsButton;

    @FXML
    void exitApp(ActionEvent event) {//Uygulamayı kapatır.
        System.exit(0);
    }

    @FXML
    void openSettings(ActionEvent event) { //Ayarlar menüsünü açar.
        try {
            Parent root= FXMLLoader.load(getClass().getResource("settings-screen.fxml"));
            settingsButton.getScene().setRoot(root);
        }catch (Exception e){
            System.out.println("Sayfa açılamadı.");
        }
    }

    @FXML
    void openStatistics(ActionEvent event) throws IOException { //İstatislik ekranını açar.
            Parent root= FXMLLoader.load(getClass().getResource("words-screen.fxml"));
            statisticsButton.getScene().setRoot(root);
    }

    @FXML
    void startExam(ActionEvent event) { //Bir test başlatır.
        try {
            Parent root= FXMLLoader.load(getClass().getResource("test-screen.fxml"));
            startButton.getScene().setRoot(root);
        }catch (Exception e){
            System.out.println("Sayfa açılamadı.");
        }
    }
}
