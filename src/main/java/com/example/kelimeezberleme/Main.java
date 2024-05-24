package com.example.kelimeezberleme;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
    
public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException { //Uygulama başlatıldığında ilk ekranı açar.
        FXMLLoader firstScreenLoader = new FXMLLoader(getClass().getResource("first-screen.fxml"));
        Scene scene = new Scene(firstScreenLoader.load());
        Image icon=new Image("Icon.png");
        stage.setTitle("Kelime Ezberleme Uygulaması");
        stage.setFullScreenExitHint("Tam Ekrandan Çıkmak İçin ESC Tuşuna Basınız");
        stage.setFullScreen(true);
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }

    public static void printNode(final Node node) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {//İstatisliğin prntlemenmesini sağlar.336333633
        Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout
                = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.HARDWARE_MINIMUM);
        PrinterAttributes attr = printer.getPrinterAttributes();
        PrinterJob job = PrinterJob.createPrinterJob();
        double scaleX
                = pageLayout.getPrintableWidth() / node.getBoundsInParent().getWidth();
        double scaleY
                = pageLayout.getPrintableHeight() / node.getBoundsInParent().getHeight();
        Scale scale = new Scale(scaleX, scaleY);
        node.getTransforms().add(scale);

        if (job != null && job.showPrintDialog(node.getScene().getWindow())) {
            boolean success = job.printPage(pageLayout, node);
            if (success) {
                job.endJob();

            }
        }
        node.getTransforms().remove(scale);
    }

    public static void main(String[] args) {
        launch();
    }
}