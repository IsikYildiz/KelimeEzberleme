package com.example.kelimeezberleme;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import java.sql.Connection;
import java.sql.SQLException;

public class SignInScreenController {
    @FXML
    private TextField answer;

    @FXML
    private Button backButton;

    @FXML
    private Label error;

    @FXML
    private ToggleGroup question;

    @FXML
    private Button signInButton;

    @FXML
    private TextField signinName;

    @FXML
    private TextField signinPassword;


    @FXML
    void goBack(ActionEvent event) { //İlk sayfaya geri dönülmesini sağlar.
        try {
            Parent root= FXMLLoader.load(getClass().getResource("first-screen.fxml"));
            backButton.getScene().setRoot(root);
        }catch (Exception e){
            System.out.println("Sayfaya Dönülemedi");
        }
    }

    @FXML
    void signIn(ActionEvent event) throws SQLException {//İsim, parola ve gmail bilgilerini alıp yeni bir kullanıcı oluşturur.
        Connection con=Data.connect();
        if(!Data.comparePassword(con,signinPassword ) && !signinPassword.getText().isEmpty()){
            if(!Data.compareName(con,signinName) && !signinName.getText().isEmpty()){
                LoginScreenController.setNameString(signinName);
                RadioMenuItem radioButton = (RadioMenuItem) question.getSelectedToggle();
                try{
                    if(!answer.getText().isEmpty()){
                        Data.insertValueUser(con, signinName, signinPassword, radioButton,answer);
                        try {
                            Parent root = FXMLLoader.load(getClass().getResource("login-screen.fxml"));
                            signInButton.getScene().setRoot(root);
                        } catch (Exception e) {
                            System.out.println("Başarısız");
                        }
                        con.close();
                    }
                    else{
                        error.setText("Lütfen cevabı giriniz.");
                        error.setVisible(true);
                    }
                }catch (NullPointerException e){
                    error.setText("Lütfen bir soru seçiniz.");
                    error.setVisible(true);
                }
            }
            else{
                error.setText("Kullanıcı adı girilmedi veya sistemde zaten kayıtlıdır.");
                error.setVisible(true);
            }
        }
        else{
            error.setText("Parola girilmedi veya sistemde zaten kayıtlıdır.");
            error.setVisible(true);
        }
    }
}
