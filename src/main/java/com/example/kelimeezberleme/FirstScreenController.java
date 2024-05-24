package com.example.kelimeezberleme;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

public class FirstScreenController {
    @FXML
    private Button signinbutton;

    @FXML
    private Button enterButton;

    @FXML
    private Button exitButton;

    @FXML
    void enterApp(ActionEvent event) { //Giriş yapma arayüzünü ekrana getirir.
        try {
            Parent root= FXMLLoader.load(getClass().getResource("login-screen.fxml"));
            enterButton.getScene().setRoot(root);
        }catch (Exception e){
            System.out.println("Giriş sayfası açılmadı");
        }
    }

    @FXML
    void signIn(ActionEvent event) {//Kayıt olma arayüzünü ekrana getirir.
        try {
            Parent root= FXMLLoader.load(getClass().getResource("signin-screen.fxml"));
            signinbutton.getScene().setRoot(root);
        }catch (Exception e){
            System.out.println("Kayıt ol sayfası açılmadı");
        }
    }

    @FXML
    void exitApp(ActionEvent event) {//Programı kapatır.
        System.exit(0);
    }
}