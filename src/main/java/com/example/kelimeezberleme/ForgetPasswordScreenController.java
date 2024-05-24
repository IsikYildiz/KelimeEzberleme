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

public class ForgetPasswordScreenController {
    @FXML
    private Label wrongAnswer;

    @FXML
    private TextField answer;

    @FXML
    private Button answerButton;

    @FXML
    private TextField newPassword1;

    @FXML
    private TextField newPassword2;

    @FXML
    private Label question;

    @FXML
    private Button saveButton;

    @FXML
    private Label differentPasswords;

    public void initialize() throws SQLException { //Sayfa ilk açıldığında soruyu gösterir.
        Connection con=Data.connect();
        question.setText(Data.findQuestion(con,LoginScreenController.getNameString())+"?");
        con.close();
    }

    @FXML
    void answerQuestion(ActionEvent event) throws SQLException { //Soru doğru cevaplanırsa yeni şifre girme ekranı açılır.
        Connection con=Data.connect();
        if(answer.getText().equals(Data.findAnswer(con,LoginScreenController.getNameString()))){
            question.setVisible(false);
            answer.setVisible(false);
            answerButton.setVisible(false);
            newPassword1.setVisible(true);
            newPassword2.setVisible(true);
            saveButton.setVisible(true);
            wrongAnswer.setVisible(false);
            con.close();
        }
        else
            wrongAnswer.setVisible(true);
    }

    @FXML
    void save(ActionEvent event) throws SQLException { //Yeni şifre kaydedilir.
        Connection con=Data.connect();
        if(newPassword1.getText().equals(newPassword2.getText()) && !newPassword1.getText().isEmpty()){
            Data.updatePassword(con,newPassword1,LoginScreenController.getNameString());
            try {
                Parent root= FXMLLoader.load(getClass().getResource("login-screen.fxml"));
                saveButton.getScene().setRoot(root);
            }catch (Exception e){
                System.out.println("Başarısız");
            }
            con.close();
        }
        else{
            differentPasswords.setText("Şifrelerde hata vardır.");
            differentPasswords.setVisible(true);
        }
    }

}
