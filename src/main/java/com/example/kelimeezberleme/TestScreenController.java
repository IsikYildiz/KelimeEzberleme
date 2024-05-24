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


}
