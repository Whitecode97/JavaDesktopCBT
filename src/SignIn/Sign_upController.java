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
import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
//import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author EVELYN
 */
public class Sign_upController implements Initializable {

    char gender = 'e' ;
    
    @FXML
    private AnchorPane signup_Anchor;
    @FXML
    private JFXTextField Name;
    @FXML
    private JFXTextField pass;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField Reg_no;
    @FXML
    private RadioButton Mgender;
     @FXML
    private ToggleGroup mgender;
    @FXML
    private RadioButton Fgender;
    @FXML
    private JFXButton signUpbttn;
    @FXML
    private JFXButton cancelBttn;
    
    AlertTemplates at = new AlertTemplates();
    DatabaseTemp dbt = new DatabaseTemp();    
    String mailPattern ="^[A-Za-z0-9]{1,50}@[A-Za-z]{1,7}.[A-Za-z]{1,5}$";
    String namePattern ="^[A-Za-z]{1,100}$";
    String passPattern ="^[A-Za-z0-9]{1,6}$";
    String regPattern ="^[0-9]{1,6}$";
    boolean fal=false;
    
    @FXML
    private Pane signuppane;
    @FXML
    private Pane admins;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        JFXDepthManager.setDepth(signuppane,10);
        JFXDepthManager.setDepth(admins,10);
        
    }
  
       
    @FXML
    private void signUp(ActionEvent event) throws SQLException {
        String studname = Name.getText();
        String password = pass.getText();
        String studEmail = email.getText();
        String Reg = Reg_no.getText();
        RadioButton rad = (RadioButton) mgender.getSelectedToggle();
        String radbtn = rad.getText();
        if(studname.isEmpty() || password.isEmpty() || studEmail.isEmpty() || Reg.isEmpty()|| radbtn.isEmpty())
         { at.alertWarning("Fields can't be empty"); }
         String myQuery = "Insert into STUDENT_SIGNUP (Regno, studname, password, Email,Gender)values('"+Reg+"', '"+studname+"', '"+password+"','"+studEmail+"','"+radbtn+"')";
         try {
            if(dbt.writeToDB(myQuery)){
                
                 admins.setVisible(true);
                    PauseTransition transition = new PauseTransition();
                   transition.setDuration(Duration.seconds(5));
                   transition.setOnFinished(evt -> {
                       admins.setVisible(false);
            try {
                Stage s  = (Stage)this.signup_Anchor.getScene().getWindow();
                s.close();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/SignIn/Sign_Inp.fxml"));
                 s.getIcons().add(new Image(getClass().getResourceAsStream("/img/2-2-education-picture.png")));
                 s.setTitle("SignInn-BellTech");
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
                
//                at.alertConfirm("Success!!");
//                Name.setText("");
//                pass.setText("");
//                email.setText("");
//                Reg_no.setText(""); 
                 }
            else{
                at.alertError("Cannot insert to DB");
                
            }
        } catch (SQLException ex) {
            at.alertError(ex.getMessage());
        }
    }
    @FXML
    private void cancel(ActionEvent event) {
        Name.clear();
        pass.clear();
        email.clear();
        Reg_no.clear();
    }
    
    @FXML
    void femaleGender(ActionEvent event) {
       // Fgender.setText("female");
    }

    @FXML
    void maleGender(ActionEvent event) {
       // Mgender.setText("male");
        
    }

    @FXML
    private void backToSignIn(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/SignIn/Sign_Inp.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void preValidate(KeyEvent event) {
        String src = event.getSource().toString() ;
        
        if(src.contains("Name")){
            Pattern a = Pattern.compile(this.namePattern) ;
            Matcher m = a.matcher(this.Name.getText()) ;

                if(m.matches()){
                    this.Name.setStyle("-fx-border-color:none");
                    this.signUpbttn.setOpacity(1);
                    this.signUpbttn.setDisable(false);

                }else{
//                    at.alertInfo("Hint: name should be alphabets only");
                    this.Name.setStyle("-fx-border-color:red");                
                    this.signUpbttn.setOpacity(0.4);
                    this.signUpbttn.setDisable(true);
                }
        }else
        if(src.contains("pass")){
            Pattern a = Pattern.compile(this.passPattern) ;
            Matcher m = a.matcher(this.pass.getText()) ;
        
            if(m.matches()){
                this.pass.setStyle("-fx-border-color:none");
                this.signUpbttn.setOpacity(1);
                this.signUpbttn.setDisable(false);                
            }else{
//                at.alertInfo("sample: okn123.\n Hint: six characters only \n No special characters allowed");
                this.pass.setStyle("-fx-border-color:red");                
                this.signUpbttn.setOpacity(0.4);
                this.signUpbttn.setDisable(true);
            }
        }else
        if(src.contains("Reg_no")){
            Pattern a = Pattern.compile(this.regPattern) ;
            Matcher m = a.matcher(this.Reg_no.getText()) ;
        
            if(m.matches()){
                this.Reg_no.setStyle("-fx-border-color:none");
                this.signUpbttn.setOpacity(1);
                this.signUpbttn.setDisable(false);                
            }else{
                
//                at.alertInfo("Sample: 080 \n Hint: 3-6 characters");
                this.Reg_no.setStyle("-fx-border-color:red");                
                this.signUpbttn.setOpacity(0.4);
                this.signUpbttn.setDisable(true);
            }
        }else
        if(src.contains("email")){
            Pattern a = Pattern.compile(this.mailPattern) ;
            Matcher m = a.matcher(this.email.getText()) ;
        
            if(m.matches()){
                this.email.setStyle("-fx-border-color:none");
                this.signUpbttn.setOpacity(1);
                this.signUpbttn.setDisable(false);                
            }else{
//                at.alertInfo("Hint: okn123@guess.com");
                this.email.setStyle("-fx-border-color:red");                
                this.signUpbttn.setOpacity(0.4);
                this.signUpbttn.setDisable(true);
            }
        }

    }

    @FXML
    private void privacy(MouseEvent event) throws IOException, URISyntaxException {
        Desktop.getDesktop().browse(new URL("http://privacytandc/www.belltech.org").toURI());
    }
   
}
