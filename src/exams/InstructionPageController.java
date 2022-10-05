/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exams;

import alerts.AlertTemplates;
import aptech_soft.DBTemplates.DatabaseTemp;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class InstructionPageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    AlertTemplates at = new AlertTemplates();
    @FXML
    private AnchorPane insanchor;
    @FXML
    private ComboBox<String> selectDept;

     DatabaseTemp dbt = new DatabaseTemp();
    @FXML
    private JFXTextField regnum;
    static String reg;
    ObservableList<String>List=FXCollections.observableArrayList("Select Course","C#","Java");
//  
    String regPattern ="^[0-9]{1,6}$";
    @FXML
    private ImageView backtomana;
    @FXML
    private JFXButton startbtn;
    @FXML
    void start(ActionEvent event) throws IOException, SQLException {
        ResultSet rs ;
       //String Studentreg= SignIn.Sign_InpController.re;
     //  regnum.setText(Studentreg);
        reg = regnum.getText();
        if(reg.isEmpty()){
            at.alertWarning("Input Registration Number");
            return;
        }
        String quer = "select regno from student_signup where regno = '"+reg+"'";
        rs = dbt.readFromDB(quer);
        while(rs.next()){
        
            
            String id = rs.getString("regno");
            if(reg.equals(id) && selectDept.getValue().contains("C#")){
                String cou = selectDept.getValue();
                String que="update student_signup set course = '"+cou+"' where regno = '"+reg+"'";
                dbt.updates(que);
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/exams/CsharpQuests.fxml"));
                AnchorPane ap = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(ap));
                stage.setTitle("C# - Exams portal");
                stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/2-2-education-picture.png" )));
                stage.setResizable(false);
                stage.show();
                Stage s = (Stage)insanchor.getScene().getWindow();
                s.close();
            }else if(selectDept.getValue().contains(" ")){at.alertInfo("Select a Course");}
            else{
               if(!reg.equals(id) ){at.alertWarning("User Not Found!");}
            }
            
        
        
        
//            String id = rs.getString("regno");
            if(reg.equals(id) && selectDept.getValue().contains("Java")){
                String cou = selectDept.getValue();
                String que= "update student_signup set course = '"+cou+"' where regno = '"+reg+"'";
                dbt.updates(que);
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/exams/JavaQuests.fxml"));
                AnchorPane ap = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(ap));
                stage.setTitle("Java - Exams portal");
                stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/2-2-education-picture.png" )));
                stage.setResizable(false);
                stage.show();
                Stage s = (Stage)insanchor.getScene().getWindow();
                s.close();
            }else if(selectDept.getValue().contains(" ")){at.alertInfo("Select a Course");}
            else{
               if(!reg.equals(id) ){at.alertWarning("User Not Found!");}}
    
        }
    }

        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        selectDept.setItems(List);
        selectDept.setValue("Select Course");
        //JFXDepthManager.setDepth(,1);
    }    

    @FXML
    private void handleback(MouseEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/SignIn/Sign_Inp.fxml"));
        AnchorPane ap = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(ap));
        stage.setTitle("");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/2-2-education-picture.png" )));
        stage.setResizable(false);
        stage.show();
        Stage s = (Stage)insanchor.getScene().getWindow();
        s.close();
    }


     @FXML
    void validate(KeyEvent event) {
      String src = event.getSource().toString();
        if(src.contains("reg")){
            Pattern a = Pattern.compile(this.regPattern) ;
            Matcher m = a.matcher(this.regnum.getText()) ;
        
            if(m.matches()){
                this.regnum.setStyle("-fx-border-color:none");
                this.startbtn.setOpacity(1);
                this.startbtn.setDisable(false);                
            }else{
                this.regnum.setStyle("-fx-border-color:red");                
                this.startbtn.setOpacity(0.4);
                this.startbtn.setDisable(true);
            }
    }

      
}} 
