/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import SignIn.OpenPageController;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import aptech_soft.DBTemplates.DatabaseTemp;
import alerts.AlertTemplates;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.effects.JFXDepthManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.animation.PauseTransition;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author EVELYN
 */
public class AdminLoginController implements Initializable {
    @FXML
    private AnchorPane adminloginanchor ;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;


    @FXML
    private Pane adminpane;

    /**
     * Initializes the controller class.
     */
    
   
    AlertTemplates at = new AlertTemplates();
    DatabaseTemp dbt = new DatabaseTemp();
    @FXML
    private JFXButton loginbtn;
    @FXML
    private ImageView bak;
    
    String namePattern ="^[A-Za-z]{1,100}$";String passPattern ="^[A-Za-z]{1,6}$";
    @FXML
    private Pane adlogin;
    @FXML
    private Pane admins;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        JFXDepthManager.setDepth(adminpane,50);
    }    

    @FXML
    private void adminlogin(ActionEvent event) throws IOException, SQLException {
         String name = username.getText();
        String passw = password.getText();
        if(name.isEmpty()|| passw.isEmpty()){
                at.alertWarning("Fields can't be empty");
        }
        String query = "select * from adminpass where username = '"+name+"' and pass = '"+passw+"'";
        ResultSet rs;
        rs = dbt.readFromDB(query);
    
       
          //String names = rs.getString("username"); String pass = rs.getString("pass");
        try {
                 if((!name.equals(name))&&((!passw.equals(passw)))){
                            at.alertError("Invalid user name and password!");
                            username.clear();
                            password.clear();}else
                    if(rs.next())
                    {admins.setVisible(true);
                    PauseTransition transition = new PauseTransition();
                   transition.setDuration(Duration.seconds(10));
                   transition.setOnFinished(evt -> {
                       admins.setVisible(false);
            try {
                Stage s  = (Stage) adminloginanchor.getScene().getWindow();
                s.close();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/admin/AdminMenu.fxml"));
                 s.getIcons().add(new Image(getClass().getResourceAsStream("/img/2-2-education-picture.png")));
                 s.setTitle("Welcome To Bell Tech");
                 s.setResizable(false);
                AnchorPane ap = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(ap));
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(OpenPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
            });
        transition.play();
                       
                    }
                    
        }catch (SQLException ex) {
            at.alertError(ex.getMessage());
        }
  
    }

    @FXML
    private void cancel(ActionEvent event) {
         username.clear();
         password.clear();
    }

       void exitButtn(MouseEvent event) throws IOException {
              System.exit(0);
    }

    @FXML
    private void handleback(MouseEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader();
                           loader.setLocation(getClass().getResource("/homePage/Index.fxml"));
                           AnchorPane ap = loader.load();
                           Stage stage = new Stage();
                           stage.setScene(new Scene(ap));
                           stage.setTitle("Admin Menu");
                           stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/2-2-education-picture.png")));
                           stage.setResizable(false);
                           stage.show();
                           Stage s = (Stage) adminloginanchor.getScene().getWindow();
                           s.close();
            
    }

    @FXML
    private void validation(KeyEvent event) {
         String src = event.getSource().toString() ;
        
        if(src.contains("username")){
            Pattern a = Pattern.compile(this.namePattern) ;
            Matcher m = a.matcher(this.username.getText()) ;

                if(m.matches()){
                    this.username.setStyle("-fx-border-color:none");
                    this.loginbtn.setOpacity(1);
                    this.loginbtn.setDisable(false);

                }else{
//                    at.alertInfo("numbers and special characters are not accepted");
                    this.username.setStyle("-fx-border-color:red");                
                    this.loginbtn.setOpacity(0.4);
                    this.loginbtn.setDisable(true);
                }
        }else
        if(src.contains("pass")){
            Pattern a = Pattern.compile(this.passPattern) ;
            Matcher m = a.matcher(this.password.getText()) ;
        
            if(m.matches()){
                this.password.setStyle("-fx-border-color:none");
                this.loginbtn.setOpacity(1);
                this.loginbtn.setDisable(false);                
            }else{
//                at.alertInfo("numbers and special characters are not accepted");
                this.password.setStyle("-fx-border-color:red");                
                this.loginbtn.setOpacity(0.4);
                this.loginbtn.setDisable(true);
            }
        }
    }
}
