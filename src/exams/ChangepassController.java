/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exams;

import alerts.AlertTemplates;
import aptech_soft.DBTemplates.DatabaseTemp;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author EVELYN
 */
public class ChangepassController implements Initializable {
    @FXML
    private JFXTextField adminName;
   
    @FXML
    private JFXButton done;
    @FXML
    private JFXButton cancel;
    @FXML
    private ImageView backarrow;
    @FXML
    private JFXTextField oldpass;
    @FXML
    private JFXTextField admin;
    @FXML
    private AnchorPane canchor;
     String namePattern ="^[A-Za-z]{1,100}$";String passPattern ="^[A-Za-z]{1,6}$";
    @FXML
    private JFXTextField newpassw;
    @FXML
    private JFXTextField confmpass;
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODOu
         
    
    }    
    DatabaseTemp dbt = new DatabaseTemp();
    AlertTemplates at = new AlertTemplates();
    //@FXML
//    private void uName(ActionEvent event) {
//    }
//

    @FXML
    private void changePass(ActionEvent event) {
        //write to an read from db
      
        String ol= oldpass.getText();
        String nw= newpassw.getText();
        String cp= confmpass.getText();
        String an= adminName.getText();
        String nan= admin.getText();
        String qu = "select * from adminpass";
       // String qu = "select username from adminpass where username = '"+an+"'";
        if(ol.isEmpty()||nw.isEmpty()||cp.isEmpty()||an.isEmpty()||nan.isEmpty()||qu.isEmpty())at.alertInfo("fields can't be empty");
        
        
           
        ResultSet rs;
        rs = dbt.readFromDB(qu);
        try {
            
            if(rs.next()){
                String query = "update SecureBell.adminpass set username = '"+nan+"', pass = '"+cp+"' where pass ='"+ol+"' ";
                if(dbt.updates(query)){
                    at.alertConfirm("success!");
                    oldpass.setText("");
                    newpassw.setText("");
                    confmpass.setText("");
                    adminName.setText("");
                    admin.setText("");
                }
                else{
                    at.alertWarning("you are missing something. Retry!");
                    oldpass.setText("");
                    newpassw.setText("");
                    confmpass.setText("");
                    adminName.setText("");
                    admin.setText("");
                }
            }else{
            
                if(!cp.equals(nw)){at.alertWarning("Confirm pass and new pass mismatch");}
            
            }
        } catch (SQLException ex) {
            at.alertWarning("Something went wrong:"+" "+ex.getMessage()); 
        }
    }

    @FXML
    private void fieldcancel(ActionEvent event) {
        oldpass.setText("");
        newpassw.setText("");
        confmpass.setText("");
        adminName.setText("");
         admin.setText("");
    }

    @FXML
    private void back(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/admin/AdminMenu.fxml"));
        AnchorPane ap = loader.load();
        Stage    stage = new Stage();
        stage.setScene(new Scene(ap));
         stage.setTitle("Home");
        stage.setResizable(false);
        stage.show();
        Stage s = (Stage)canchor.getScene().getWindow();
        s.close();
    }

    @FXML
    private void validation(KeyEvent event) {
        
        String src = event.getSource().toString();
       
       if(src.contains("newpassw")){
            Pattern a = Pattern.compile(this.passPattern) ;
            Matcher m = a.matcher(this.newpassw.getText()) ;
        
            if(m.matches()){
//                at.alertInfo("sample: ballOut.\n Hint: 4-6 letters only \n No special characters allowed");
                this.newpassw.setStyle("-fx-border-color:none");
                this.done.setOpacity(1);
                this.done.setDisable(false);                
            }else{
                
                this.newpassw.setStyle("-fx-border-color:red");                
                this.done.setOpacity(0.4);
                this.done.setDisable(true);
            }
       }
            if(src.contains("confmpass")){
            Pattern c = Pattern.compile(this.passPattern) ;
            Matcher cp = c.matcher(this.confmpass.getText()) ;
        
            if(cp.matches()){
//                at.alertInfo("sample: ballOut.\n Hint: 4-6 letters only \n No special characters or numbers allowed");
                this.confmpass.setStyle("-fx-border-color:none");
                this.done.setOpacity(1);
                this.done.setDisable(false);                
            }else{
                
                this.confmpass.setStyle("-fx-border-color:red");                
                this.done.setOpacity(0.4);
                this.done.setDisable(true);
            }
        
    }
            
            if(src.contains("admin")){
            Pattern nae= Pattern.compile(this.namePattern) ;
            Matcher nam = nae.matcher(this.admin.getText()) ;
        
            if(nam.matches()){
//                at.alertInfo("sample: suhnny.\n Hint: six characters only \n No special characters or numbers allowed");
                this.admin.setStyle("-fx-border-color:none");
                this.done.setOpacity(1);
                this.done.setDisable(false);                
            }else{
                
                this.admin.setStyle("-fx-border-color:red");                
                this.done.setOpacity(0.4);
                this.done.setDisable(true);
            }
        
    }
    

    }
    }   
    
    

