/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SignIn;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author EVELYN
 */
public class OpenPageController implements Initializable {
     @FXML
    private AnchorPane anchorOpen;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
    
       
        // TODO
        PauseTransition transition = new PauseTransition();
        transition.setDuration(Duration.seconds(10));
        transition.setOnFinished(event -> {
            try {
                Stage s  = (Stage) anchorOpen.getScene().getWindow();
                s.close();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/homePage/Index.fxml"));
                 s.getIcons().add(new Image(getClass().getResourceAsStream("/img/2-2-education-picture.png")));
                 s.setTitle("Welcome To Bell Tech");
                 s.setResizable(false);
                AnchorPane ap = loader.load();
                Stage stage = new Stage();
                 stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(new Scene(ap));
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(OpenPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
            });
        transition.play();
      
    }}
/*}*/
