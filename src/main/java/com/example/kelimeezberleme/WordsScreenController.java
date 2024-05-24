package com.example.kelimeezberleme;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Window;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class WordsScreenController{
    @FXML
    private TableColumn<Words, String> no;

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<Words, String> consecutiveCorrectNo;

    @FXML
    private TableColumn<Words, String > correctNo;

    @FXML
    private TableColumn<Words, LocalDate> lastDate;

    @FXML
    private TableColumn<Words, String > sentence;

    @FXML
    private TableView<Words> table;

    @FXML
    private TableColumn<Words, String> turkish;

    @FXML
    private TableColumn<Words, String> english;

    @FXML
    private Button printButton;

    public void initialize() throws SQLException { //Sayfa ilk açıldığında kullanıcının kelimeler tablosunu gösterir.
        Connection con=Data.connect();
        english.setCellValueFactory(new PropertyValueFactory<>("english"));
        turkish.setCellValueFactory(new PropertyValueFactory<>("turkish"));
        sentence.setCellValueFactory(new PropertyValueFactory<>("sentence"));
        no.setCellValueFactory(new PropertyValueFactory<>("no"));
        correctNo.setCellValueFactory(new PropertyValueFactory<>("correctNo"));
        consecutiveCorrectNo.setCellValueFactory(new PropertyValueFactory<>("consecutiveCorrectNo"));
        lastDate.setCellValueFactory(new PropertyValueFactory<>("lastDate"));
        ObservableList<Words> wordsList = FXCollections.observableArrayList(WordsData.getList(con,LoginScreenController.getNameString()));
        table.setItems(wordsList);
        con.close();
    }

    @FXML
    void goBack(ActionEvent event) { //Ana menüye geri döner.
        try {
            Parent root= FXMLLoader.load(getClass().getResource("mainmenu-screen.fxml"));
            backButton.getScene().setRoot(root);
        }catch (Exception e){
            System.out.println("Sayfa açılamadı.");
        }
    }

    @FXML
    void print(ActionEvent event) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {//Ekranı printler.
        Scene a=table.getScene();
        Main.printNode(a.getRoot());
    }
}
