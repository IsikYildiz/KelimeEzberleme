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
    void answerQuestion(ActionEvent event) throws SQLException {//Alınan listedeki kelimeler bitene kadar sorular sorulur.
        Connection con=Data.connect();
        if(!answer.getText().isEmpty()){
            if(answer.getText().equalsIgnoreCase(WordsData.getTurkish(con,words.get(i)))){
                WordsData.updateCorrectWord(con,words.get(i));
                if(!words.get(i).equals(words.getLast())){
                    question.setText("'"+words.get(i+1)+"' kelimesinin türkçe karşılığı nedir?");
                    i++;
                    con.close();
                }
                else{
                    answer.setVisible(false);
                    answerButton.setVisible(false);
                    question.setVisible(false);
                    endButton.setVisible(true);
                }
            }
            else {
                WordsData.updateWrongWord(con,words.get(i));
                if(!words.get(i).equals(words.getLast())) {
                    question.setText("'" + words.get(i + 1) + "' kelimesinin türkçe karşılığı nedir?");
                    i++;
                    con.close();
                }
                else{
                    answer.setVisible(false);
                    answerButton.setVisible(false);
                    question.setVisible(false);
                    endButton.setVisible(true);
                }
            }
        }
        con.close();
    }
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
