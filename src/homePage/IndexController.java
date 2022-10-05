/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homePage;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.Desktop;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

  
  
public class IndexController implements Initializable {

       
       @FXML
    private FontAwesomeIconView about;

      @FXML
    private Pane aboutpane;
    @FXML
    private AnchorPane inanchor;
    @FXML
    private JFXButton admin;
    @FXML
    private JFXButton close;
    @FXML
    private AnchorPane anchorP;
    @FXML
    private FontAwesomeIconView close1;
    @FXML
    private Button Confirm;
    @FXML
    private Button nope;
    
    @FXML
    void admin(ActionEvent event) throws IOException {
FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/admin/AdminLogin.fxml"));
         AnchorPane ap = loader.load();
        Stage stage = new Stage();
       stage.setScene(new Scene(ap));
         stage.setTitle("Admin Login");
           stage.setResizable(false);
         stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/2-2-education-picture.png")));
        stage.show();
        Stage s = (Stage) inanchor.getScene().getWindow();
        s.close();
    }

    @FXML
    void student(ActionEvent event) throws IOException {
       FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/SignIn/Sign_Inp.fxml"));
        AnchorPane ap = loader.load();
        Stage    stage = new Stage();
        stage.setScene(new Scene(ap));
         stage.setTitle("Student Sign_in");
        stage.setResizable(false);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/2-2-education-picture.png")));
        stage.show();
        Stage s = (Stage)inanchor.getScene().getWindow();
        s.close();
    }
      
   
     void exit(MouseEvent event) {
     System.exit(0);
    }
   @FXML
    void about(MouseEvent event) {
       aboutpane.setVisible(true);
    }
    
      @FXML
    void close(ActionEvent event) {
       aboutpane.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void exitapp(MouseEvent event) {
        anchorP.setVisible(true);
    }

    @FXML
    private void handlePopcl(MouseEvent event) {
        anchorP.setVisible(false);
    }

    @FXML
    private void handleCon(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void handleNope(ActionEvent event) {
        anchorP.setVisible(false);
    }

    @FXML
    private void help(MouseEvent event) throws MalformedURLException, IOException, URISyntaxException {
        Desktop.getDesktop().browse(new URL("http://help/www.belltech.org").toURI());
    }
    
}
