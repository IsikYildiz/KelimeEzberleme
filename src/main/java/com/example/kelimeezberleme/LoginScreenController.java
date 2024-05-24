package com.example.kelimeezberleme;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.SQLException;
import javafx.scene.control.Label;


public class LoginScreenController {
    private static String nameString;

    @FXML
    private Button backButton;

    @FXML
    private Button forgetButton;

    @FXML
    private Button loginButton;

    @FXML
    private Label loginError;

    @FXML
    private TextField loginName;

    @FXML
    private TextField loginPassword;

    @FXML
    private Label noName;

    @FXML
    void goBack(ActionEvent event) { //İlk ekrana geri döner.
        try {
            Parent root= FXMLLoader.load(getClass().getResource("first-screen.fxml"));
            forgetButton.getScene().setRoot(root);
        }catch (Exception e){
            System.out.println("Sayfaya Dönülemedi");
        }
    }

    @FXML
    void forgetPassword(ActionEvent event) throws SQLException { //Bir isim girildikten sonra şifremi unuttum ekranına yönlendirir.
        Connection con=Data.connect();
        if(!loginName.getText().isEmpty()){
            if(Data.compareName(con,loginName)){
                setNameString(loginName);
                try {
                    Parent root= FXMLLoader.load(getClass().getResource("forgetpassword-screen.fxml"));
                    backButton.getScene().setRoot(root);
                }catch (Exception e){
                    System.out.println("Sayfaya Dönülemedi");
                }
            }
            else
                noName.setVisible(true);
        }
        else
            noName.setVisible(true);
    }

    @FXML
    void login(ActionEvent event) throws SQLException { //İsim ve şifre bilgileri doğru girilince ana menüye yönlendirir.
        Connection con=Data.connect();
        if(Data.compareUser(con,loginName,loginPassword) && !loginName.getText().isEmpty() && !loginPassword.getText().isEmpty()){
            setNameString(loginName);
            try {
                Parent root= FXMLLoader.load(getClass().getResource("mainmenu-screen.fxml"));
                loginButton.getScene().setRoot(root);
            }catch (Exception e){
                System.out.println("Başarısız");
            }
            con.close();
        }
        else
            loginError.setVisible(true);
    }

    public static String getNameString(){ //Kullanıcının adını String olarak döndürür.
        return nameString;
    }

    public static void setNameString(TextField name){//Kullanıcının adını String olarak kaydeder.
        nameString=name.getText();
    }
}
