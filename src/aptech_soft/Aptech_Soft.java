/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aptech_soft;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author EVELYN
 */
public class Aptech_Soft extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
     Parent root = FXMLLoader.load(getClass().getResource("/aptech_soft/Open_page.fxml"));
//      Parent root = FXMLLoader.load(getClass().getResource("/exams/InstructionPage.fxml"));
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED);
//        stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/2-2-education-picture.png" )));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
