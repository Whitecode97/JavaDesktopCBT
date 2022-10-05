/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SignIn;

import alerts.AlertTemplates;
import aptech_soft.DBTemplates.DatabaseTemp;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author EVELYN
 */
public class Sign_InpController implements Initializable {
    @FXML
    private AnchorPane signinacnhor;
    @FXML
    public JFXTextField RegField;
    
    @FXML
    private JFXButton signinBttn;
    @FXML
    private JFXButton cancel;
     
    AlertTemplates at= new AlertTemplates();
    DatabaseTemp db = new DatabaseTemp();
    @FXML
    private Pane signoutpro;
    @FXML
    private Pane admins;
    @FXML
    private Pane signfor;
    
    @FXML
    private JFXTextField passfield;
        
         

    /**
     * initialize the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  //TODO
        JFXDepthManager.setDepth(admins, 50);
        JFXDepthManager.setDepth(signoutpro, 50);
         JFXDepthManager.setDepth(signfor, 50);
    }    
       String passPattern ="^[A-Za-z0-9]{1,6}$";
       String regPattern ="^[0-9]{1,6}$";
   
    @FXML
    private void create(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/SignIn/Sign_up.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/2-2-education-picture.png")));
        stage.setTitle("Signup-Belltech");
        stage.initStyle(StageStyle.DECORATED);
        stage.show();
        Stage s = (Stage)signinacnhor.getScene().getWindow();
        s.close();
    }
            @FXML
    void preValidate(KeyEvent event) {
       String src = event.getSource().toString();
       
       if(src.contains("pass")){
            Pattern a = Pattern.compile(this.passPattern) ;
            Matcher m = a.matcher(this.passfield.getText()) ;
        
            if(m.matches()){
                this.passfield.setStyle("-fx-border-color:none");
                this.signinBttn.setOpacity(1);
                this.signinBttn.setDisable(false);                
            }else{
//                at.alertInfo("sample: okn123.\n Hint: six characters only \n No special characters allowed");
                this.passfield.setStyle("-fx-border-color:red");                
                this.signinBttn.setOpacity(0.4);
                this.signinBttn.setDisable(true);
            }
        }else
        if(src.contains("Reg")){
            Pattern a = Pattern.compile(this.regPattern) ;
            Matcher m = a.matcher(this.RegField.getText()) ;
        
            if(m.matches()){
                this.RegField.setStyle("-fx-border-color:none");
                this.signinBttn.setOpacity(1);
                this.signinBttn.setDisable(false);                
            }else{
//                at.alertInfo("Sample: 080 \n Hint: 3-6 characters");
                this.RegField.setStyle("-fx-border-color:red");                
                this.signinBttn.setOpacity(0.4);
                this.signinBttn.setDisable(true);
            }
        }
    }
   

    @FXML
    private void signin(ActionEvent event) throws IOException, SQLException {
                String pa=  passfield.getText();
                 String re =  RegField.getText();
           if(pa.isEmpty() || re.isEmpty())
           { at.alertWarning("Fields can't be empty"); }
         ResultSet rs; 
//           String query = "SELECT * from STUDENT_SIGNUP where password = '"+pa+"' and regno = '"+re+"' "; 
         String query = "SELECT password from STUDENT_SIGNUP where password = '"+pa+"' and regno = '"+re+"' "; 
               
                try{
                     rs = this.db.readFromDB(query);
                if(rs.next()){
                    
                     admins.setVisible(true);
                    PauseTransition transition = new PauseTransition();
                   transition.setDuration(Duration.seconds(5));
                   transition.setOnFinished(evt -> {
                       admins.setVisible(false);
            try {
                Stage s  = (Stage) signinacnhor.getScene().getWindow();
                s.close();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/exams/InstructionPage.fxml"));
                 s.getIcons().add(new Image(getClass().getResourceAsStream("/img/2-2-education-picture.png")));
                 s.setTitle("Read Instructions");
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
                  
                } else
                   if(!rs.next())
                   {at.alertInfo("You may have entered incorrect details");
                   this.at.alertError("User Not Found!"); }   
                        }                 
             catch(SQLException ex){
                System.err.println("this is what happened " + ex.getMessage());
            }
    }
    @FXML
    private void cancelbttn(ActionEvent event) {
        passfield.clear();
        RegField.clear();
        //Email.clear();
    }
    @FXML
    void Signout(ActionEvent event) throws IOException {
        
        
        PauseTransition transition = new PauseTransition();
        signinacnhor.setOpacity(60);
        signoutpro.setVisible(true);
        transition.setDuration(Duration.seconds(5));
        transition.setOnFinished(e -> {
            try {
                
                signoutpro.setVisible(false);
                Stage s  = (Stage)signinacnhor .getScene().getWindow();
                s.close();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/homePage/Index.fxml"));
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
      
    }}
        
       

    
