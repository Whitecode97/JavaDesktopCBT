/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homePage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author EVELYN
 */
public class HomepageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
       @FXML
    private AnchorPane hanchor;

    @FXML
    void start(ActionEvent event) throws IOException {
      FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/SignIn/Sign_Inp.fxml"));
        AnchorPane ap = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(ap));
         stage.setTitle("Sign in");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/2-2-education-picture.png" )));
        stage.setResizable(false);
        stage.show();
        Stage s = (Stage)hanchor.getScene().getWindow();
        s.close();
    }
    
    @FXML
    private void exit(MouseEvent event) {
      
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
