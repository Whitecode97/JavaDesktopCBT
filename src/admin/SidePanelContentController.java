/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author EVELYN
 */
public class SidePanelContentController implements Initializable {
    @FXML
    private Pane slide;
    @FXML
    private JFXButton b1;
    @FXML
    private JFXButton b2;
    @FXML
    private JFXButton b3;
    @FXML
    private JFXButton exit;
     
      private Object stage;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void studentrecords(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/admin/StudentRecords.fxml"));
        AnchorPane ap = loader.load();
        Stage stag = new Stage();
        stag.setScene(new Scene(ap));
         stag.setTitle("Student Records");
        stag.getIcons().add(new Image(getClass().getResourceAsStream("/img/2-2-education-picture.png" )));
        stag.setResizable(false);
        stag.show();
        Stage s = (Stage)slide.getScene().getWindow();
        s.close(); 
        
    }
     @FXML
    void changepassword(ActionEvent event) throws IOException {
      FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/admin/Changepass.fxml"));
        AnchorPane ap = loader.load();
        Stage sta = new Stage();
        sta.setScene(new Scene(ap));
         sta.setTitle("Manage Questions");
        sta.getIcons().add(new Image(getClass().getResourceAsStream("/img/2-2-education-picture.png" )));
        sta.setResizable(false);
        sta.show();
        Stage s = (Stage)exit.getScene().getWindow();
        s.close(); 
    }

    @FXML
    private void ManageQuestions(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/admin/ManaQuest.fxml"));
        AnchorPane ap = loader.load();
        Stage s = new Stage();
        s.setScene(new Scene(ap));
         s.setTitle("Manage Questions");
        s.getIcons().add(new Image(getClass().getResourceAsStream("/img/2-2-education-picture.png" )));
        s.setResizable(false);
        s.show();
        Stage st = (Stage)exit.getScene().getWindow();
        st.close(); 
        
    }

    @FXML
    private void exit(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/homePage/Index.fxml"));
        AnchorPane ap = loader.load();
        Stage s = new Stage();
        s.setScene(new Scene(ap));
         s.setTitle("BellTech-Home");
        s.getIcons().add(new Image(getClass().getResourceAsStream("/img/2-2-education-picture.png" )));
        s.setResizable(false);
        s.show();
        Stage st = (Stage)exit.getScene().getWindow();
        st.close(); 
    }

//    @FXML
//    private void studrec(MouseEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("/admin/StudentRecords.fxml"));
//        AnchorPane ap = loader.load();
//        Stage stage = new Stage();
//        stage.setScene(new Scene(ap));
//         stage.setTitle("Student Records");
//        stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/2-2-education-picture.png" )));
//        stage.setResizable(false);
//        stage.show();
//        Stage s = (Stage)slide.getScene().getWindow();
//        s.close(); 
//        
//    }

   
   
    
}
