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
import java.util.List;

public class TestScreenController {

    @FXML
    private TextField answer;

    @FXML
    private Button answerButton;

    @FXML
    private Label question;

    @FXML
    private Button startTestButton;

    @FXML
    private Button endButton;

    private int i=0;

    private List<String> words;
    @FXML
    void startTest(ActionEvent event) throws SQLException {//Test başlatılır.
        Connection con=Data.connect();
        words=WordsData.getTestList(con,LoginScreenController.getNameString());
        answer.setVisible(true);
        answerButton.setVisible(true);
        question.setVisible(true);
        question.setText("'"+words.get(i)+"' kelimesinin türkçe karşılığı nedir?");
        startTestButton.setVisible(false);
        con.close();
    }
    @FXML
    void endTest(ActionEvent event) { //Testi bitirip ana menüye dönülür.
        try {
            Parent root = FXMLLoader.load(getClass().getResource("mainmenu-screen.fxml"));
            endButton.getScene().setRoot(root);
        } catch (Exception e) {
            System.out.println("Başarısız");
        }
    }
}
