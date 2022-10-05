/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import alerts.AlertTemplates;
import aptech_soft.DBTemplates.DatabaseTemp;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
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
//import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
//import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author EVELYN
 */
public class ManaQuestController implements Initializable {
    @FXML
    private JFXTextArea Qbox;
    @FXML
    private JFXTextField OpA;
    @FXML
    private JFXTextField OpB;
    @FXML
    private JFXTextField OpC;
    @FXML
    private JFXTextField Ans;
    @FXML
    private JFXTextField Qid;
//    @FXML
//    private JFXButton save;
    @FXML
    private JFXButton clear;
    
    @FXML
    private ImageView backarrow;
    
    @FXML
    private Label prompt;
    @FXML
    private ComboBox<String> selectcourse;
     @FXML
    private AnchorPane manchor;

    /**
     * Initializes the controller class.
     */
    AlertTemplates at = new AlertTemplates();
    DatabaseTemp dbt = new DatabaseTemp();
    @FXML
    private JFXButton save;
    @FXML
    private JFXButton modify;
    @FXML
    private JFXButton updatebtn;
    @FXML
    private JFXButton Delete;
   
    String QuestPattern ="^[0-9]{1,2}$";
       String OptionPattern ="^[A-Z]{1}$";
       
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selectcourse.setItems(course);
        selectcourse.setValue("Select Course");
        
    }    
   
     ObservableList<String> course = FXCollections.observableArrayList("Select Course","C#","Java");


    @FXML
    private void saveQuestion(ActionEvent event) {
      String qnum = Qid.getText();
      String qbox = Qbox.getText();
      String OA=    OpA.getText();
      String OB =   OpB.getText();
      String OC =   OpC.getText();
      String ans =  Ans.getText();
      if(qnum.isEmpty() || qbox.isEmpty() || OA.isEmpty() || OB.isEmpty()|| OC.isEmpty() || ans.isEmpty())
         { at.alertWarning("Fields can't be empty"); }
       String alphabets = "abcdefghijklmnopqrstuvwxyz";
       if (Qid.getText().contains(alphabets))at.alertInfo("Question no: Numbers only.");
      if(selectcourse.getValue().equals("Java")){
      String myQuery = "Insert into SecureBell.javaquest (questid,questions,optA,optB,optC,answers) values('"+qnum+"', '"+qbox+"', '"+OA+"','"+OB+"','"+OC+"','"+ans+"')";
        try {
            if(dbt.writeToDB(myQuery)){
                prompt.setText("Save succeeded!");
                Qid.setText("");
                Qbox.setText("");
                OpA.setText("");
                OpB.setText("");
                OpC.setText("");
                Ans.setText("");
            }
            else{
                
                at.alertError("Cannot insert to DB");
                
            }
        } catch (SQLException ex) {
            at.alertError(ex.getMessage());
        }}
      
      if(selectcourse.getValue().equals("C#")){
      String myQuery = "Insert into SecureBell.csharp (questid,questions,optA,optB,optC,answers) values('"+qnum+"', '"+qbox+"', '"+OA+"','"+OB+"','"+OC+"','"+ans+"')";
        try {
         
            if(dbt.writeToDB(myQuery)){
                prompt.setText("Save Succedeed!");
                Qid.setText("");
                Qbox.setText("");
                OpA.setText("");
                OpB.setText("");
                OpC.setText("");
                Ans.setText("");
                
            }
            else 
            {
                
                at.alertError("Cannot insert to DB");
                
            }
        } catch (SQLException ex) {
            at.alertError(ex.getMessage());
        }}
      
      
     
      
      }

    @FXML
    private void ClearFields(ActionEvent event) {
        Qid.clear();
        Qbox.clear(); OpA.clear();OpB.clear(); OpC.clear(); Ans.clear(); prompt.setText(""); 
    }

    @FXML
    private void opentab (ActionEvent event) throws IOException {
        prompt.setText("");
        FXMLLoader ld = new FXMLLoader();
        ld.setLocation(getClass().getResource("/admin/ManageOpen.fxml"));
        AnchorPane an = ld.load();
        Stage st = new Stage();
        st.setScene(new Scene(an));
        st.setTitle("Open-ManageQuestions");
        st.getIcons().add(new Image(getClass().getResourceAsStream("/img/2-2-education-picture.png")));
        st.setResizable(false);
        st.show();
        //new obj of stage + cast anchorpane into stage
        Stage sa = (Stage) manchor.getScene().getWindow();
        sa.close();
    }

    @FXML
    private void modifyQuestion(ActionEvent event) throws SQLException {
       updatebtn.setVisible(true);
       Delete.setVisible(true);
        if(selectcourse.getValue().equals("Java")){
         String id = Qid.getText();
        String qu = "select * from Javaquest where Questid = '"+id+"'" ;
        ResultSet rs = dbt.readFromDB(qu);
        
            
            boolean isFound =true;
            
            while(rs.next())
            {
                String questn = rs.getString("questions");
                String o1 = rs.getString("opta");
                String o2 = rs.getString("optb");
                 String o3 = rs.getString("optc");
                String ans = rs.getString("answers");
                Qbox.setText(questn);
                OpA.setText(o1);
                 OpB.setText(o2);
                 OpC.setText(o3);
                 Ans.setText(ans);
                isFound =true;
            }
                if (!isFound) {
                at.alertWarning(" Question doesn't Exist");
            }
            
        
        }
        
        if(selectcourse.getValue().equals("C#")){
         String id = Qid.getText();
        String qu = "select *from csharp where Questid = '"+id+"'" ;
        ResultSet rs = dbt.readFromDB(qu);
        
            
            boolean isFound =true;
            
            while(rs.next())
            {
                String questn = rs.getString("questions");
                String o1 = rs.getString("opta");
                String o2 = rs.getString("optb");
                 String o3 = rs.getString("optc");
                String ans = rs.getString("answers");
                Qbox.setText(questn);
                OpA.setText(o1);
                 OpB.setText(o2);
                 OpC.setText(o3);
                 Ans.setText(ans);
                isFound =true;
            }
                if (!isFound) {
                at.alertWarning(" Question doesn't Exist");
            }
            
        
        }
    }

    
     @FXML
    void back(MouseEvent event) throws IOException {
        prompt.setText("");
      FXMLLoader ld = new FXMLLoader();
        ld.setLocation(getClass().getResource("/admin/AdminMenu.fxml"));
        AnchorPane an = ld.load();
        Stage st = new Stage();
        st.setScene(new Scene(an));
        st.setTitle("Admin Menu");
        st.getIcons().add(new Image(getClass().getResourceAsStream("/img/2-2-education-picture.png")));
        st.setResizable(false);
        st.show();
         Stage sa = (Stage) manchor.getScene().getWindow();
        sa.close();
   
    }

    @FXML
    private void updateQuest(ActionEvent event) {
        prompt.setText("");
      String qnum = Qid.getText();
      String qbox = Qbox.getText();
      String OA=    OpA.getText();
      String OB =   OpB.getText();
      String OC =   OpC.getText();
      String ans =  Ans.getText();
         if(selectcourse.getValue().equals("Java")){
      String myQuery = "update javaquest set questions = '"+qbox+"', opta = '"+OA+"', optb = '"+OB+"',optc ='"+OC+"',answers = '"+ans+"'where questid = '"+qnum+"'";
        try {
            dbt.updates(myQuery);
            if(dbt.updates(myQuery)){
                prompt.setText("Save succeeded!");
                Qid.setText("");
                Qbox.setText("");
                OpA.setText("");
                OpB.setText("");
                OpC.setText("");
                Ans.setText("");
            }else
                  if(!dbt.updates(myQuery)){
                      
                      at.alertError("Cannot Update fields to DB");
                      
                  }
      }
         catch (SQLException ex) {
            at.alertError(ex.getMessage());
        }
         }
         
         
         
         if(selectcourse.getValue().equals("C#")){
          try {
              String Query = "update csharp set questions ='"+qbox+"',opta='"+OA+"', optb='"+OB+"',optc='"+OC+"',answers= '"+ans+"'where questid = '"+qnum+"'";
              
              dbt.updates(Query);
              if(dbt.updates(Query)){
                  prompt.setText("Save succeeded!");
                  Qid.setText("");
                  Qbox.setText("");
                  OpA.setText("");
                  OpB.setText("");
                  OpC.setText("");
                  Ans.setText("");
              }
              
              else
                  if(!dbt.updates(Query)){
                      
                      at.alertError("Cannot Update fields to DB");
                      
                  }
          } catch (SQLException ex) {
              at.alertWarning(ex.getMessage());
          }
}else { at.alertError("Cannot Update fields to DB");}
         
         
         

}

    @FXML
    private void deleteQuest(ActionEvent event) {
        prompt.setText("");
         String qnum = Qid.getText();
      String qbox = Qbox.getText();
      String OA=    OpA.getText();
      String OB =   OpB.getText();
      String OC =   OpC.getText();
      String ans =  Ans.getText();
         if(selectcourse.getValue().equals("Java")){
      String myQuery = "delete from javaquest where questid = '"+qnum+"'";
        try {
           // at.alertWarning("Really want to delete?");
             dbt.updates(myQuery);
            if(dbt.updates(myQuery)){
                prompt.setText("Deleted");
                Qid.setText("");
                Qbox.setText("");
                OpA.setText("");
                OpB.setText("");
                OpC.setText("");
                Ans.setText("");
            }else
                  if(!dbt.updates(myQuery)){
                      
                      at.alertError("Unable to delete fields from DB");
                      
                  }
      }
         catch (SQLException ex) {
            at.alertError(ex.getMessage());
        }
         }
         
         
         
         if(selectcourse.getValue().equals("C#")){
          try {
              String Query = "delete from csharp where questid = '"+qnum+"'";
              
              dbt.updates(Query);
              if(dbt.updates(Query)){
                  prompt.setText("deleted");
                  Qid.setText("");
                  Qbox.setText("");
                  OpA.setText("");
                  OpB.setText("");
                  OpC.setText("");
                  Ans.setText("");
              }
              
              else
                  if(!dbt.updates(Query)){
                      
                      at.alertError("unable to delete fields from DB");
                      
                  }
          } catch (SQLException ex) {
              at.alertWarning(ex.getMessage());
          }
}else { at.alertError("Unable to delete fields from DB");}
         
        
    }

    @FXML
    private void validate(KeyEvent event) {
        String src = event.getSource().toString();
       
      
        if(src.contains("Ans")){
            Pattern a = Pattern.compile(this.OptionPattern) ;
            Matcher m = a.matcher(this.Ans.getText()) ;
        
            if(m.matches()){
                this.Ans.setStyle("-fx-border-color:none");
                this.save.setOpacity(1);
//                 this.Delete.setOpacity(1);
//                  this.modify.setOpacity(1);
//                   this.updatebtn.setOpacity(1);
                this.save.setDisable(false);
//                this.Delete.setDisable(false);
//                this.modify.setDisable(false);
//                this.updatebtn.setDisable(false);
            }else{
                
                this.Ans.setStyle("-fx-border-color:red");                
                this.save.setOpacity(4.0);
//                 this.Delete.setOpacity(4.0);
                  this.modify.setOpacity(4.0);
//                   this.updatebtn.setOpacity(4.0);
                this.save.setDisable(true);
//                this.Delete.setDisable(true);
                this.modify.setDisable(true);
//                this.updatebtn.setDisable(true);
            }
        }
    }
}